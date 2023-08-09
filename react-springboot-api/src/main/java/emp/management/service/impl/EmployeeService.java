package emp.management.service.impl;

import emp.management.payload.EmployeeDto;
import emp.management.entity.Employee;
import emp.management.exception.IllegalArgumentException;
import emp.management.exception.ResourceNotFoundException;
import emp.management.repository.EmployeeRepository;
import emp.management.service.IService;
import emp.management.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

import static emp.management.constants.AppMessages.EMP_NOT_EXIST_ID;

@Component
public class EmployeeService implements IService<EmployeeDto> {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public List<EmployeeDto> getList(int skip, int limit) {

        final PageRequest request = PageRequest.of(skip, limit, Sort.Direction.ASC, "firstName");
        List<Employee> empList = this.repository.findAll(request).toList();
        return mapperUtil.mapList(empList, EmployeeDto.class);
    }

    @Override
    public EmployeeDto create(EmployeeDto dto) throws IllegalArgumentException {

        if (StringUtils.hasText(dto.getFirstName()) && StringUtils.hasText(dto.getLastName())
                && StringUtils.hasText(dto.getEmailId())) {
            dto.setEmailId(StringUtils.trimAllWhitespace(dto.getEmailId()));
            Employee emp = this.mapperUtil.map(dto, Employee.class);
            return this.mapperUtil.map(this.repository.save(emp), EmployeeDto.class);
        } else {
            throw (new IllegalArgumentException("Not all mandatory fields can be empty."));
        }
    }

    @Override
    public EmployeeDto getById(Long id) {

        Employee employee = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EMP_NOT_EXIST_ID + id));
        return this.mapperUtil.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateById(Long id, EmployeeDto dto) {

        Employee dbEmployee = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EMP_NOT_EXIST_ID + id));

        if (StringUtils.hasText(dto.getFirstName())) {
            dbEmployee.setFirstName(dto.getFirstName());
        }
        if (StringUtils.hasText(dto.getLastName())) {
            dbEmployee.setLastName(dto.getLastName());
        }
        if (StringUtils.hasText(dto.getEmailId())) {
            dbEmployee.setEmailId(dto.getEmailId());
        }
        return this.mapperUtil.map(this.repository.save(dbEmployee), EmployeeDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Employee dbEmployee = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EMP_NOT_EXIST_ID + id));

        this.repository.delete(dbEmployee);
    }
}

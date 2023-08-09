package emp.management.service;

import emp.management.payload.EmployeeDto;
import emp.management.exception.IllegalArgumentException;

import java.util.List;

public interface IService<T> {

    List<T> getList(int skip, int limit);

    T create(EmployeeDto dto) throws IllegalArgumentException;

    T getById(Long id);

    T updateById(Long id, EmployeeDto dto);

    void deleteById(Long id);
}

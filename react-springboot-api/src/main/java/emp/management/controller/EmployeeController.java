package emp.management.controller;

import emp.management.exception.IllegalArgumentException;
import emp.management.exception.ResourceNotFoundException;
import emp.management.model.Employee;
import emp.management.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static emp.management.constants.AppMessages.EMP_NOT_EXIST_ID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// get all Employees Rest API
	@Operation(summary = "Get List of all Employees")
	@SecurityRequirement(name = "JWT")
	@GetMapping(value = "/employeesList")
	public List<Employee> getEmployeesList(@RequestParam int skip, @RequestParam int limit) {
		
		final PageRequest request = PageRequest.of(skip, limit, Sort.Direction.ASC, "firstName");
		return this.employeeRepository.findAll(request).toList();
	}

	// Create / Add a new employee REST API
	@Operation(summary = "Create / add a new employee")
	@PostMapping(value = "/employeesList")
	public Employee addEmployee(@RequestBody Employee employee) throws IllegalArgumentException {
		if(StringUtils.hasText(employee.getFirstName()) && StringUtils.hasText(employee.getLastName()) && StringUtils.hasText(employee.getEmailId())) {
			employee.setEmailId(StringUtils.trimAllWhitespace(employee.getEmailId()));
			return this.employeeRepository.save(employee);
		} else {
			throw( new IllegalArgumentException("Not all mandatory fields can be empty."));
		}
	}

	// Get Employee by id rest api
	@Operation(summary = "Get Employee by id rest api")
	@GetMapping("/employeesList/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable Long id) {

		Employee employee = this.employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(EMP_NOT_EXIST_ID + id));
		return ResponseEntity.ok(employee);
	}
	
	//update Employee rest api
	@Operation(summary = "update Employee rest api")
	@PutMapping("/employeesList/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Employee dbEmployee = this.employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(EMP_NOT_EXIST_ID + id));
		
		if(StringUtils.hasText(employee.getFirstName())) {
			dbEmployee.setFirstName(employee.getFirstName());
		}
		if(StringUtils.hasText(employee.getLastName())) {
			dbEmployee.setLastName(employee.getLastName());
		}
		if(StringUtils.hasText(employee.getEmailId())) {
			dbEmployee.setEmailId(employee.getEmailId());
		}
		return ResponseEntity.ok(this.employeeRepository.save(dbEmployee));
	}
	
	@Operation(summary = "delete Employee rest api")
	@DeleteMapping("/employeesList/{empId}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long empId){
		Employee dbEmployee = this.employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException(EMP_NOT_EXIST_ID + empId));
		
		this.employeeRepository.delete(dbEmployee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}

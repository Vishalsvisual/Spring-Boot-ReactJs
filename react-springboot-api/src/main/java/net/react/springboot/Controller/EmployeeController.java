package net.react.springboot.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import net.react.springboot.exception.IllegalArgumentException;
import net.react.springboot.exception.ResourceNotFoundException;
import net.react.springboot.model.Employee;
import net.react.springboot.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// get all Employees Rest API
	@Operation(summary = "Get List of all Employees")
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
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist with ID : " + id));
		return ResponseEntity.ok(employee);
	}
	
	//update Employee rest api
	@Operation(summary = "update Employee rest api")
	@PutMapping("/employeesList/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Employee dbEmployee = this.employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist with ID : " + id));
		
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
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist with ID : " + empId));
		
		this.employeeRepository.delete(dbEmployee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}

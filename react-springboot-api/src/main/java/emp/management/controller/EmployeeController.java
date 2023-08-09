package emp.management.controller;

import emp.management.payload.EmployeeDto;
import emp.management.exception.IllegalArgumentException;
import emp.management.service.impl.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // get all Employees Rest API
    @Operation(summary = "Get List of all Employees")
    @SecurityRequirement(name = "JWT")
    @GetMapping(value = "/employeesList")
    public List<EmployeeDto> getEmployeesList(@RequestParam int skip, @RequestParam int limit) {
        return this.service.getList(skip, limit);
    }

    // Create / Add a new employee REST API
    @Operation(summary = "Create / add a new employee")
    @PostMapping(value = "/employeesList")
    public EmployeeDto addEmployee(@RequestBody EmployeeDto request) throws IllegalArgumentException {
        return this.service.create(request);
    }

    // Get Employee by id rest api
    @Operation(summary = "Get Employee by id rest api")
    @GetMapping("/employeesList/{id}")
    public ResponseEntity<EmployeeDto> getEmpById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getById(id));
    }

    //update Employee rest api
    @Operation(summary = "update Employee rest api")
    @PutMapping("/employeesList/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto request) {
        return ResponseEntity.ok(this.service.updateById(id, request));
    }

    @Operation(summary = "delete Employee rest api")
    @DeleteMapping("/employeesList/{empId}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long empId) {

        this.service.deleteById(empId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

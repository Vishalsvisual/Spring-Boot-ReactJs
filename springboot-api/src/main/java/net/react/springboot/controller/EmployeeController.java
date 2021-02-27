package net.react.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.react.springboot.model.Employee;
import net.react.springboot.repository.EmployeeRepository;


@RestController
@RequestMapping("api/v1/")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value="/employees", method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    
}

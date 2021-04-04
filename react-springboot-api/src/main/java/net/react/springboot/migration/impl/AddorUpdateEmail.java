package net.react.springboot.migration.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.react.springboot.migration.Migration;
import net.react.springboot.model.Employee;
import net.react.springboot.repository.EmployeeRepository;


/**
 * Migration Query for the data which do not have email id.
 * Here the Email id is generated using first name and employee id
 * 
 * @author Vishal Kumar
 *
 */

@Component
@Migration(context = "document", id = "generate-email_id", sequence = 1, author = "VISHAL KUMAR")
public class AddorUpdateEmail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(AddorUpdateEmail.class);
	
	@Autowired
	private transient EmployeeRepository employeeRepository;
	
	public void execute() {
		LOGGER.info(" generate-email_id :: =>  :: START");
		
		// fetch all Employees data from database
		List<Employee> allEmployees = this.employeeRepository.findAll();
		
		List<Employee> updatedList = new ArrayList<>();
		
		for( Employee emp : allEmployees) {
			
			if(StringUtils.isBlank(emp.getEmailId())) {
				String email = emp.getFirstName() + emp.getId() + "@emp-mail.in";
				emp.setEmailId(email.toLowerCase());
				updatedList.add(emp);
				System.out.println(emp.getEmailId());
			}
		}
		
		this.employeeRepository.saveAll(updatedList);
		LOGGER.info(" generate-email_id :: =>  :: END");
	}
}

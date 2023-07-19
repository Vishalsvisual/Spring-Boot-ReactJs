package emp.management.migration.impl;

import emp.management.migration.Migration;
import emp.management.entity.Employee;
import emp.management.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Migration Query for the data which do not have email id.
 * Here the Email id is generated using first name and employee id
 *
 * @author Vishal Kumar
 */

@Component
@Migration(context = "document", id = "generate-email_id", sequence = 1, author = "VISHAL KUMAR")
@Slf4j
public class AddOrUpdateEmail implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient EmployeeRepository employeeRepository;

    public void execute() {
        log.info(" generate-email_id :: =>  :: START");

        // fetch all Employees data from database
        List<Employee> allEmployees = this.employeeRepository.findAll();
        List<Employee> updatedList = new ArrayList<>();

        for (Employee emp : allEmployees) {

            if (StringUtils.hasText(emp.getEmailId())) {
                String email = emp.getFirstName() + emp.getId() + "@emp-mail.in";
                emp.setEmailId(email.toLowerCase());
                updatedList.add(emp);
                log.info(emp.getEmailId());
            }
        }

        this.employeeRepository.saveAll(updatedList);
        log.info(" generate-email_id :: =>  :: END");
    }
}

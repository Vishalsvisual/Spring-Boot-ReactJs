package net.react.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

/**
 * @Author Vishal Kumnar
 * Model class for Employee
 */
@Entity
@Table(name = "employees")
@Data
public class
 Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId; 
    
}

package emp.management.entity;

import emp.management.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "is_address_same")
    private Boolean isAddressSame;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address")
    private Address permanentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "residential_address")
    private Address residentialAddress;

    public Employee(String firstName, String lastName, String emailId) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public void setResidentialAddress(Address residentialAddress){

        if(Boolean.TRUE.equals(isAddressSame)){
            this.residentialAddress = permanentAddress;
        } else {
            this.residentialAddress = residentialAddress;
        }
    }
}


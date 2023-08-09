package emp.management.payload;

import emp.management.enums.Gender;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private long id;

    private String firstName;

    private String lastName;

    @Email
    private String emailId;

    private Date birthDate;

    private Gender gender;

    private Boolean isAddressSame;

    private AddressDto permanentAddress;

    private AddressDto residentialAddress;

    public EmployeeDto(String firstName, String lastName, String emailId) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }
}


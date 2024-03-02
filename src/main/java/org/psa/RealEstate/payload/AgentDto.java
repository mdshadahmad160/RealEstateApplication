package org.psa.RealEstate.payload;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AgentDto {

    private long id;
    @NotNull(message = "Name Should Not Be Null: ")
    private String name;

    @Email(message = "Invalid Email Address:")
    private String email;
    @NotNull
    @Pattern(regexp = "^\\d{10}$",message = "Invalid Mobile Number Entered: ")
    private String phoneNumber;
}

package org.psa.RealEstate.payload;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UserDto {
    @NotNull(message = "UserName Should Not Be Null: ")
    private String username;
    private String firstName;
    private String lastName;
    @NotNull(message = "Must be 10 Digits: ")
    private String phoneNumber;
    @NotEmpty
    private String address;
    @NotNull(message = "Password Should Be Unique: ")
    private String password;
    @NotNull(message = "Email Should Be Unique: ")
    private String email;

    private LocalDateTime registrationDate;
    private LocalDateTime lastLoginDate;
    private boolean active;
}

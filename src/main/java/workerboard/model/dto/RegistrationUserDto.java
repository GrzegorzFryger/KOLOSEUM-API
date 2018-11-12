package workerboard.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import workerboard.model.Role;
import workerboard.model.enums.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RegistrationUserDto implements Serializable
{
    @NotNull
    @NotBlank(message = "Name not be null")
    private String registrationName;

    @NotNull
    @NotBlank(message = "Surname not be null")
    private String registrationSurname;

    @NotNull(message = "Email not be null")
    @NotBlank(message = "Email not be null")
    @Email(message = "Valid email address")
    private String registrationEmail;

    @NotNull(message = "Password not be null")
    @NotBlank(message = "Password not be null")
    private String registrationPassword;

    @JsonIgnore
    private Set<Role> userRole = new HashSet<>();

    public RegistrationUserDto() {



    }

    public String getRegistrationName() {
        return registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public String getRegistrationSurname() {
        return registrationSurname;
    }

    public void setRegistrationSurname(String registrationSurname) {
        this.registrationSurname = registrationSurname;
    }

    public String getRegistrationEmail() {
        return registrationEmail;
    }

    public void setRegistrationEmail(String registrationEmail) {
        this.registrationEmail = registrationEmail;
    }

    public String getRegistrationPassword() {
        return registrationPassword;
    }

    public void setRegistrationPassword(String registrationPassword) {
        this.registrationPassword = registrationPassword;
    }

    public Set<Role> getUserRole() {

        return userRole;
    }

    public void setUserRole(Set<Role> userRole) {
        this.userRole = userRole;
    }
}

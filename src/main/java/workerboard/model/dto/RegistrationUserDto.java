package workerboard.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import workerboard.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
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
    private String email;

    @NotNull(message = "Password not be null")
    @NotBlank(message = "Password not be null")
    private String password;

    @JsonIgnore
    private Set<Role> userRole = new HashSet<>();

    public RegistrationUserDto() {



    }

    public String getRegistrationName() {
        return registrationName;
    }

    public String getRegistrationSurname() {
        return registrationSurname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getUserRole() {
        return userRole;
    }
}

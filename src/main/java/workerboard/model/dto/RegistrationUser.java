package workerboard.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import workerboard.model.UserRole;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RegistrationUser implements Serializable
{
    @NotNull
    private String registrationName;
    @NotNull
    private String registrationSurname;
    @NotNull
    private String registrationEmail;
    @NotNull
    @Min(8)
    private String registrationPassword;
    @JsonIgnore
    private final UserRole userRole = UserRole.UNSPECIFED;

    public RegistrationUser() {
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

    public UserRole getUserRole() {
        return userRole;
    }
}

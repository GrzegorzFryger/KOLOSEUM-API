package workerboard.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import workerboard.model.enums.UserRole;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class RegistrationUser implements Serializable
{
    @NotNull
    private String registrationName;
    @NotNull
    private String registrationSurname;
    @NotNull
    private String registrationEmail;
    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationUser)) return false;
        RegistrationUser that = (RegistrationUser) o;
        return Objects.equals(registrationName, that.registrationName) &&
                Objects.equals(registrationSurname, that.registrationSurname) &&
                Objects.equals(registrationEmail, that.registrationEmail) &&
                Objects.equals(registrationPassword, that.registrationPassword) &&
                userRole == that.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationName, registrationSurname, registrationEmail, registrationPassword, userRole);
    }

    @Override
    public String toString() {
        return "RegistrationUser{" +
                "registrationName='" + registrationName + '\'' +
                ", registrationSurname='" + registrationSurname + '\'' +
                ", registrationEmail='" + registrationEmail + '\'' +
                ", registrationPassword='" + registrationPassword + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}

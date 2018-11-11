package workerboard.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginDto {

    @NotNull(message = "Email not be null")
    @NotBlank(message = "Email not be null")
    @Email(message = "Valid email address")
    private String email;
    @NotNull(message = "Password not be null")
    @NotBlank(message = "Password not be null")
    private String password;

    public LoginDto() {
    }

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

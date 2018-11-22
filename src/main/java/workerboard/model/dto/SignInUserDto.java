package workerboard.model.dto;

import workerboard.model.ApplicationUser;

public class SignInUserDto {
    ApplicationUser applicationUser;
    String token;
    String tokenType;

    public SignInUserDto() {
    }

    public SignInUserDto(ApplicationUser applicationUser, String token, String tokenType) {
        this.applicationUser = applicationUser;
        this.token = token;
        this.tokenType = tokenType;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}

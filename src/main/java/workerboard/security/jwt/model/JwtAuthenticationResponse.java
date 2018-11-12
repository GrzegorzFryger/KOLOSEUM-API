package workerboard.security.jwt.model;

import com.fasterxml.jackson.annotation.JsonView;
import workerboard.model.dto.ViewsForApplicationUser;

public class JwtAuthenticationResponse {

    @JsonView(ViewsForApplicationUser.Basic.class)
    private String accessToken;
    @JsonView(ViewsForApplicationUser.Basic.class)
    private String tokenType = "Bearer";


    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;

    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }


}
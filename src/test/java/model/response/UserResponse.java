package model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    private boolean success;
    private String message;
    private UserFields user;
    private String accessToken;
    private String refreshToken;

    public UserResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public UserResponse(boolean success, UserFields user, String accessToken, String refreshToken) {
        this.success = success;
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserFields {
        private String email;
        private String name;
    }
}
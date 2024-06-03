package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    private boolean success;
    private String message;
    private User user;
    private String accessToken;
    private String refreshToken;

    public UserResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public UserResponse(boolean success, User user, String accessToken, String refreshToken) {
        this.success = success;
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserFields {
        private String email;
        private String name;
    }
}
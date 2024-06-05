package test.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.request.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static test.user.UserMethod.*;

@RunWith(Parameterized.class)
public class LoginUserTest extends BaseUserTest {

    public LoginUserTest(User user) {
        super(user);
    }

    @Test
    @DisplayName("Should login existing user")
    @Description("Login test for /api/auth/login endpoint - POST method")
    public void shouldLoginExistingUser() {
        createdResponse = createUser(user);
        Response response = loginUser(user);
        checkSuccessfulLoginResponseAndStatusCode(response);
        deleteUser(getAccessToken(createdResponse));
    }

    @Test
    @DisplayName("Should not login existing user with wrong email and password")
    @Description("Checking login with wrong auth data for /api/auth/login endpoint - POST method")
    public void shouldNotLoginExistingUserWithWrongAuthData() {
        Response response = loginUser(user);
        checkFailedLoginResponseAndStatusCode(response);
    }

}
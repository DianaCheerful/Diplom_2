package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import model.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import test.api.UserMethod;

import static test.api.CommonMethod.checkResponseAndStatusCode;

@RunWith(Parameterized.class)
public class CreateUserTest extends BaseUserTest {

    public CreateUserTest(User user) {
        super(user);
    }

    @Test
    @DisplayName("Should create unique user")
    @Description("Create test for /api/auth/register endpoint - POST method")
    public void shouldCreateUniqueUser() {
        Response response = UserMethod.createUser(user);
        checkResponseAndStatusCode(response);
        String accessToken = response.body().as(UserResponse.class).getAccessToken();
        UserMethod.deleteUser(accessToken);
    }
}

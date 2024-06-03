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

import static test.api.UserMethod.*;

@RunWith(Parameterized.class)
public class CreateUserTest extends BaseUserTest {

    public CreateUserTest(User user) {
        super(user);
    }

    @Test
    @DisplayName("Should create unique user")
    @Description("Create test for /api/auth/register endpoint - POST method")
    public void shouldCreateUniqueUser() {
        Response response = createUser(user);
        checkUserCreatedResponseAndStatusCode(response);
        String accessToken = response.body().as(UserResponse.class).getAccessToken();
        UserMethod.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Should not create already existed user")
    @Description("Checking double creating for /api/auth/register endpoint - POST method")
    public void shouldNotCreateUniqueUserTwice() {
        Response response = createUser(user);
        String accessToken = response.body().as(UserResponse.class).getAccessToken();
        Response secondResponse = createUser(user);
        checkUserAlreadyExistsResponseAndStatusCode(secondResponse);
        UserMethod.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Should not create unique user without mandatory fields")
    @Description("Checking lack of User data for /api/auth/register endpoint - POST method")
    public void shouldNotCreateUniqueUserWithoutName() {
        Response response = createUser(new User(user.getEmail(), user.getPassword()));
        checkEmptyRequiredFieldsResponseAndStatusCode(response);
    }

}
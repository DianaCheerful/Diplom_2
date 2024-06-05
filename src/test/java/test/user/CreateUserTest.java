package test.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.request.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import test.BaseTest;

import static test.user.UserTestStep.*;

@RunWith(Parameterized.class)
public class CreateUserTest extends BaseTest {

    public CreateUserTest(User user) {
        super(user);
    }

    @Test
    @DisplayName("Should create unique user")
    @Description("Create test for /api/auth/register endpoint - POST method")
    public void shouldCreateUniqueUser() {
        createdResponse = createUser(user);
        checkUserCreatedResponseAndStatusCode(createdResponse);
        deleteUser(getAccessToken(createdResponse));
    }

    @Test
    @DisplayName("Should not create already existed user")
    @Description("Checking double creating for /api/auth/register endpoint - POST method")
    public void shouldNotCreateUniqueUserTwice() {
        createdResponse = createUser(user);
        Response response = createUser(user);
        checkUserAlreadyExistsResponseAndStatusCode(response);
        deleteUser(getAccessToken(createdResponse));
    }

    @Test
    @DisplayName("Should not create unique user without mandatory fields")
    @Description("Checking lack of User data for /api/auth/register endpoint - POST method")
    public void shouldNotCreateUniqueUserWithoutName() {
        createdResponse = createUser(new User(user.getEmail(), user.getPassword()));
        checkEmptyRequiredFieldsResponseAndStatusCode(createdResponse);
    }

}
package test.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import test.BaseTest;

import static constant.TestConstants.USER_SAMPLE_DATA;
import static test.user.UserTestStep.*;

@RunWith(Parameterized.class)
public class LoginUserTest extends BaseTest {

    public LoginUserTest(User user) {
        super(user);
    }

    @Before
    public void init(){
        createdResponse = createUser(user);
    }

    @After
    public void finish(){
        deleteUser(getAccessToken(createdResponse));
    }

    @Test
    @DisplayName("Should login existing user")
    @Description("Login test for /api/auth/login endpoint - POST method")
    public void shouldLoginExistingUser() {
        Response response = loginUser(user);
        checkSuccessfulLoginResponseAndStatusCode(response);
    }

    @Test
    @DisplayName("Should not login existing user with wrong email and password")
    @Description("Checking login with wrong auth data for /api/auth/login endpoint - POST method")
    public void shouldNotLoginExistingUserWithWrongAuthData() {
        Response response = loginUser(new User(user.getEmail(), USER_SAMPLE_DATA.getPassword()));
        checkFailedLoginResponseAndStatusCode(response);
    }

}
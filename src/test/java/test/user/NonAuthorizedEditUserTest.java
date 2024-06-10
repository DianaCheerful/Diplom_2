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

import static constant.TestConstants.EMPTY_STRING;
import static constant.TestConstants.USER_SAMPLE_DATA;
import static test.user.UserTestStep.*;

@RunWith(Parameterized.class)
public class NonAuthorizedEditUserTest extends BaseTest {

    public NonAuthorizedEditUserTest(User user) {
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
    @DisplayName("Should not change existing user email without auth")
    @Description("Edit user email without auth test for /api/auth/login endpoint - PATCH method")
    public void shouldNotEditExistingUserEmailWithoutAuth() {
        Response response = editUser(
                EMPTY_STRING,
                new User(USER_SAMPLE_DATA.getEmail(), user.getPassword(), user.getName())
        );
        checkEmptyTokenResponseAndStatusCode(response);
    }

    @Test
    @DisplayName("Should not change existing user password without auth")
    @Description("Edit user password without auth test for /api/auth/login endpoint - PATCH method")
    public void shouldNotEditExistingUserPasswordWithoutAuth() {
        Response response = editUser(
                EMPTY_STRING,
                new User(user.getEmail(), USER_SAMPLE_DATA.getPassword(), user.getName())
        );
        checkEmptyTokenResponseAndStatusCode(response);
    }

    @Test
    @DisplayName("Should not change existing user name without auth")
    @Description("Edit user name without auth test for /api/auth/login endpoint - PATCH method")
    public void shouldNotEditExistingUserNameWithoutAuth() {
        Response response = editUser(
                EMPTY_STRING,
                new User(user.getEmail(), user.getPassword(), USER_SAMPLE_DATA.getName())
        );
        checkEmptyTokenResponseAndStatusCode(response);
    }
}
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
public class AuthorizedEditUserTest extends BaseTest {

    public AuthorizedEditUserTest(User user) {
        super(user);
    }

    private static Response editedUserResponse;

    @Before
    public void init(){
        createdResponse = createUser(user);
    }

    @After
    public void finish(){
        deleteUser(getAccessToken(editedUserResponse));
    }

    @Test
    @DisplayName("Should edit existing user with auth")
    @Description("Edit user with auth test for /api/auth/login endpoint - PATCH method")
    public void shouldEditExistingUserWithAuth() {
        Response response = editUser(getAccessToken(createdResponse), USER_SAMPLE_DATA);
        checkSuccessfulEditResponseAndStatusCode(response);
        editedUserResponse = loginUser(new User(USER_SAMPLE_DATA.getEmail(), USER_SAMPLE_DATA.getPassword()));
    }

    @Test
    @DisplayName("Should change existing user email with auth")
    @Description("Edit user email with auth test for /api/auth/login endpoint - PATCH method")
    public void shouldEditExistingUserEmailWithAuth() {
        Response response = editUser(
                getAccessToken(createdResponse),
                new User(USER_SAMPLE_DATA.getEmail(), user.getPassword(), user.getName())
        );
        checkSuccessfulEditResponseAndStatusCode(response);
        editedUserResponse = loginUser(new User(USER_SAMPLE_DATA.getEmail(), user.getPassword()));
    }

    @Test
    @DisplayName("Should change existing user password with auth")
    @Description("Edit user password with auth test for /api/auth/login endpoint - PATCH method")
    public void shouldEditExistingUserPasswordWithAuth() {
        Response response = editUser(
                getAccessToken(createdResponse),
                new User(user.getEmail(), USER_SAMPLE_DATA.getPassword(), user.getName())
        );
        checkSuccessfulEditResponseAndStatusCode(response);
        editedUserResponse = loginUser(new User(user.getEmail(), USER_SAMPLE_DATA.getPassword()));
    }

    @Test
    @DisplayName("Should change existing user name with auth")
    @Description("Edit user name with auth test for /api/auth/login endpoint - PATCH method")
    public void shouldEditExistingUserNameWithAuth() {
        Response response = editUser(
                getAccessToken(createdResponse),
                new User(user.getEmail(), user.getPassword(), USER_SAMPLE_DATA.getName())
        );
        checkSuccessfulEditResponseAndStatusCode(response);
        editedUserResponse = loginUser(user);
    }

}
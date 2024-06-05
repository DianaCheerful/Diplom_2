package test.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.request.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static constant.TestConstants.EMPTY_STRING;
import static constant.TestConstants.USER_EDIT_DATA;
import static test.user.UserMethod.*;

@RunWith(Parameterized.class)
public class EditUserTest extends BaseUserTest {

    public EditUserTest(User user) {
        super(user);
    }

    @Test
    @DisplayName("Should edit existing user with auth")
    @Description("Edit user with auth test for /api/auth/login endpoint - PATCH method")
    public void shouldEditExistingUserWithAuth() {
        createdResponse = createUser(user);
        Response response = editUser(getAccessToken(createdResponse), USER_EDIT_DATA);
        checkSuccessfulEditResponseAndStatusCode(response);
        Response editedUserResponse = loginUser(new User(USER_EDIT_DATA.getEmail(), USER_EDIT_DATA.getPassword()));
        deleteUser(getAccessToken(editedUserResponse));
    }

    @Test
    @DisplayName("Should change existing user email with auth")
    @Description("Edit user email with auth test for /api/auth/login endpoint - PATCH method")
    public void shouldEditExistingUserEmailWithAuth() {
        createdResponse = createUser(user);
        Response response = editUser(
                getAccessToken(createdResponse),
                new User(USER_EDIT_DATA.getEmail(), user.getPassword(), user.getName())
        );
        checkSuccessfulEditResponseAndStatusCode(response);
        Response editedUserResponse = loginUser(new User(USER_EDIT_DATA.getEmail(), user.getPassword()));
        deleteUser(getAccessToken(editedUserResponse));
    }

    @Test
    @DisplayName("Should change existing user password with auth")
    @Description("Edit user password with auth test for /api/auth/login endpoint - PATCH method")
    public void shouldEditExistingUserPasswordWithAuth() {
        createdResponse = createUser(user);
        Response response = editUser(
                getAccessToken(createdResponse),
                new User(user.getEmail(), USER_EDIT_DATA.getPassword(), user.getName())
        );
        checkSuccessfulEditResponseAndStatusCode(response);
        Response editedUserResponse = loginUser(new User(user.getEmail(), USER_EDIT_DATA.getPassword()));
        deleteUser(getAccessToken(editedUserResponse));
    }

    @Test
    @DisplayName("Should change existing user name with auth")
    @Description("Edit user name with auth test for /api/auth/login endpoint - PATCH method")
    public void shouldEditExistingUserNameWithAuth() {
        createdResponse = createUser(user);
        Response response = editUser(
                getAccessToken(createdResponse),
                new User(user.getEmail(), user.getPassword(), USER_EDIT_DATA.getName())
        );
        checkSuccessfulEditResponseAndStatusCode(response);
        Response editedUserResponse = loginUser(user);
        deleteUser(getAccessToken(editedUserResponse));
    }

    @Test
    @DisplayName("Should not change existing user email without auth")
    @Description("Edit user email without auth test for /api/auth/login endpoint - PATCH method")
    public void shouldNotEditExistingUserEmailWithoutAuth() {
        createdResponse = createUser(user);
        Response response = editUser(
                EMPTY_STRING,
                new User(USER_EDIT_DATA.getEmail(), user.getPassword(), user.getName())
        );
        checkEmptyTokenResponseAndStatusCode(response);
        deleteUser(getAccessToken(createdResponse));
    }

    @Test
    @DisplayName("Should not change existing user password without auth")
    @Description("Edit user password without auth test for /api/auth/login endpoint - PATCH method")
    public void shouldNotEditExistingUserPasswordWithoutAuth() {
        createdResponse = createUser(user);
        Response response = editUser(
                EMPTY_STRING,
                new User(user.getEmail(), USER_EDIT_DATA.getPassword(), user.getName())
        );
        checkEmptyTokenResponseAndStatusCode(response);
        deleteUser(getAccessToken(createdResponse));
    }

    @Test
    @DisplayName("Should not change existing user name without auth")
    @Description("Edit user name without auth test for /api/auth/login endpoint - PATCH method")
    public void shouldNotEditExistingUserNameWithoutAuth() {
        createdResponse = createUser(user);
        Response response = editUser(
                EMPTY_STRING,
                new User(user.getEmail(), user.getPassword(), USER_EDIT_DATA.getName())
        );
        checkEmptyTokenResponseAndStatusCode(response);
        deleteUser(getAccessToken(createdResponse));
    }
}
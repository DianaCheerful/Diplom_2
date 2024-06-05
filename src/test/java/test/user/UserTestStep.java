package test.user;

import io.qameta.allure.Step;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import model.request.User;
import model.response.UserResponse;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;

import static constant.TestConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;

public class UserTestStep {

    @Step("Send POST-request /api/auth/register")
    public static Response createUser(User user) {
        return given()
                .header(HTTP.CONTENT_TYPE, JSON_TYPE)
                .body(user)
                .post(CREATE_USER_METHOD);
    }

    @Step("Send POST-request /api/auth/login to login user")
    public static Response loginUser(User user) {
        return given()
                .header(HTTP.CONTENT_TYPE, JSON_TYPE)
                .body(new User(user.getEmail(), user.getPassword()))
                .post(LOGIN_USER_METHOD);
    }

    @Step("Send DELETE-request /api/auth/user to delete user")
    public static void deleteUser(String accessToken) {
        given()
                .header(AUTHORIZATION_HEADER, accessToken)
                .delete(EDIT_USER_METHOD);
    }

    @Step("Send PATCH-request /api/auth/user to edit user")
    public static Response editUser(String accessToken, User newUserData) {
        return given()
                .headers(new Headers(
                        new Header(HTTP.CONTENT_TYPE, JSON_TYPE),
                        new Header(AUTHORIZATION_HEADER, accessToken))
                )
                .body(newUserData)
                .patch(EDIT_USER_METHOD);
    }

    @Step("Check that user create response is success and http status code is 200")
    public static void checkUserCreatedResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(true))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, blankOrNullString())
                .and().statusCode(HttpStatus.SC_OK);
    }

    @Step("Check user already exists message and http status code is 403")
    public static void checkUserAlreadyExistsResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(false))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, equalTo(USER_ALREADY_EXISTS_MESSAGE))
                .and().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Step("Check empty required fields message and http status code is 403")
    public static void checkEmptyRequiredFieldsResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(false))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, equalTo(EMPTY_REQUIRED_FIELDS_MESSAGE))
                .and().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Step("Check successful login response and http status code is 200")
    public static void checkSuccessfulLoginResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(true))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, blankOrNullString())
                .and().statusCode(HttpStatus.SC_OK);
    }

    @Step("Check failed login response and http status code is 401")
    public static void checkFailedLoginResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(false))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, equalTo(INCORRECT_EMAIL_OR_PASSWORD_MESSAGE))
                .and().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Step("Check successful edit response and http status code is 200")
    public static void checkSuccessfulEditResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(true))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, blankOrNullString())
                .and().statusCode(HttpStatus.SC_OK);
    }

    @Step("Check empty token response and http status code is 401")
    public static void checkEmptyTokenResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(false))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, equalTo(EMPTY_TOKEN_MESSAGE))
                .and().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Step("Get accessToken from user created response")
    public static String getAccessToken(Response response) {
        return response.body().as(UserResponse.class).getAccessToken();
    }


}
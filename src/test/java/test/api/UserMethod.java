package test.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.User;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;

import static constant.TestConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;

public class UserMethod {

    @Step("Send POST-request /api/auth/register with correct data")
    public static Response createUser(User user) {
        return given()
                .header(HTTP.CONTENT_TYPE, JSON_TYPE)
                .body(user)
                .post(CREATE_USER_METHOD);
    }

    @Step("Check that response is success and http status code is 200")
    public static void checkUserCreatedResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(true))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, blankOrNullString())
                .and().statusCode(HttpStatus.SC_OK);
    }

    @Step("Check that response is not success and http status code is 403")
    public static void checkUserAlreadyExistsResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(false))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, equalTo(USER_ALREADY_EXISTS_MESSAGE))
                .and().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Step("Check that response is not success and http status code is 403")
    public static void checkEmptyRequiredFieldsResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(false))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, equalTo(EMPTY_REQUIRED_FIELDS_MESSAGE))
                .and().statusCode(HttpStatus.SC_FORBIDDEN);
    }



    @Step("Send DELETE-request /api/auth/user to delete user")
    public static void deleteUser(String accessToken) {
        given()
                .header(AUTHORIZATION_HEADER, accessToken)
                .delete(DELETE_USER_METHOD);
    }


}
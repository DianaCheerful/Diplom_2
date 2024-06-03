package test.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static constant.TestConstants.RESPONSE_MESSAGE_FIELD;
import static constant.TestConstants.RESPONSE_SUCCESS_FIELD;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;

public class CommonMethod {
    @Step("Check that response is success and http status code is 200")
    public static void checkResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(true))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, blankOrNullString())
                .and().statusCode(HttpStatus.SC_OK);
    }
}

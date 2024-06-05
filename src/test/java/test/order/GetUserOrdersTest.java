package test.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.BaseTest;

import static constant.TestConstants.USER_SAMPLE_DATA;
import static test.order.OrderTestStep.*;
import static test.user.UserTestStep.*;

public class GetUserOrdersTest extends BaseTest {

    public GetUserOrdersTest() {

    }

    private static String accessToken;

    @Before
    public void init() {
        createdResponse = createUser(USER_SAMPLE_DATA);
        accessToken = getAccessToken(createdResponse);
        createOrderWithAuth(accessToken, getRandomIngredients());
    }

    @After
    public void finish() {
        deleteUser(accessToken);
    }

    @Test
    @DisplayName("Should get user orders with auth")
    @Description("Get user orders test with authorization for /api/orders endpoint - GET method")
    public void shouldGetUserOrdersWithAuth() {
        Response response = getUserOrdersWithAuth(accessToken);
        checkUserOrderGetWithAuthResponseAndStatusCode(response);
    }

    @Test
    @DisplayName("Should not get user orders without auth")
    @Description("Get user orders test without authorization for /api/orders endpoint - GET method")
    public void shouldNotGetUserOrdersWithoutAuth() {
        Response response = getUserOrdersWithoutAuth();
        checkUserOrderGetWithoutAuthResponseAndStatusCode(response);
    }

}
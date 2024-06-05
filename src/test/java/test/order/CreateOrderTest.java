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

public class CreateOrderTest extends BaseTest {

    public CreateOrderTest() {

    }

    @Before
    public void init() {
        createdResponse = createUser(USER_SAMPLE_DATA);
    }

    @After
    public void finish() {
        deleteUser(getAccessToken(createdResponse));
    }

    @Test
    @DisplayName("Should create order with auth")
    @Description("Create test for /api/orders endpoint - POST method")
    public void shouldCreateOrderWithAuth() {
        Response response = createOrderWithAuth(getAccessToken(createdResponse), getRandomIngredients());
        checkOrderCreatedResponseAndStatusCode(response);
    }


}
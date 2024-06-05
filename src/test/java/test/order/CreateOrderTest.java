package test.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.request.Ingredients;
import org.junit.Test;
import test.BaseTest;

import static constant.TestConstants.SAMPLE_INGREDIENT_ID;
import static constant.TestConstants.USER_SAMPLE_DATA;
import static test.order.OrderTestStep.*;
import static test.user.UserTestStep.*;

public class CreateOrderTest extends BaseTest {

    public CreateOrderTest() {

    }

    @Test
    @DisplayName("Should create an order with auth")
    @Description("Create test with authorization for /api/orders endpoint - POST method")
    public void shouldCreateOrderWithAuth() {
        createdResponse = createUser(USER_SAMPLE_DATA);
        String accessToken = getAccessToken(createdResponse);
        Response response = createOrderWithAuth(accessToken, getRandomIngredients());
        checkOrderCreatedResponseAndStatusCode(response);
        deleteUser(accessToken);
    }

    @Test
    @DisplayName("Should create an order without auth")
    @Description("Create test without authorization for /api/orders endpoint - POST method")
    public void shouldCreateOrderWithoutAuth() {
        Response response = createOrderWithoutAuth(getRandomIngredients());
        checkOrderCreatedResponseAndStatusCode(response);
    }

    @Test
    @DisplayName("Should not create an order without ingredients")
    @Description("Create test without ingredients for /api/orders endpoint - POST method")
    public void shouldNotCreateOrderWithoutIngredients() {
        Response response = createOrderWithoutAuth(new Ingredients());
        checkOrderCreateFailedWithoutIngredientsResponseAndStatusCode(response);
    }

    @Test
    @DisplayName("Should not create an order with wrong ingredient id")
    @Description("Create test with wrong ingredient id for /api/orders endpoint - POST method")
    public void shouldNotCreateOrderWithWrongIngredientId() {
        Response response = createOrderWithoutAuth(new Ingredients(SAMPLE_INGREDIENT_ID));
        checkOrderCreateFailedWithWrongIngredientsIdsResponseAndStatusCode(response);
    }

}
package test.order;

import io.qameta.allure.Step;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import model.request.Ingredient;
import model.request.Ingredients;
import model.response.IngredientResponse;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static constant.TestConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OrderTestStep {

    @Step("Send POST-request /api/orders with authorization header")
    public static Response createOrderWithAuth(String accessToken, Ingredients ingredients) {
        return given()
                .headers(new Headers(
                        new Header(HTTP.CONTENT_TYPE, JSON_TYPE),
                        new Header(AUTHORIZATION_HEADER, accessToken))
                )
                .body(ingredients)
                .post(ORDER_METHOD);
    }

    @Step("Send POST-request /api/orders without authorization header")
    public static Response createOrderWithoutAuth(Ingredients ingredients) {
        return given()
                .header(HTTP.CONTENT_TYPE, JSON_TYPE)
                .body(ingredients)
                .post(ORDER_METHOD);
    }

    @Step("Check that order create response is success and http status code is 200")
    public static void checkOrderCreatedResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(true))
                .and().assertThat().body(ORDER_NAME_FIELD, notNullValue())
                .and().assertThat().body(ORDER_FIELD, notNullValue())
                .and().statusCode(HttpStatus.SC_OK);
    }

    @Step("Check that order create response is failed without ingredients and http status code is 400")
    public static void checkOrderCreateFailedWithoutIngredientsResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(false))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, equalTo(EMPTY_INGREDIENTS_MESSAGE))
                .and().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Step("Check that order create response is failed with wrong ingredients ids and http status code is 400")
    public static void checkOrderCreateFailedWithWrongIngredientsIdsResponseAndStatusCode(Response response) {
        response
                .then().assertThat().body(RESPONSE_SUCCESS_FIELD, equalTo(false))
                .and().assertThat().body(RESPONSE_MESSAGE_FIELD, equalTo(WRONG_INGREDIENTS_IDS_MESSAGE))
                .and().statusCode(HttpStatus.SC_BAD_REQUEST);
    }


    @Step("Get three random ingredients from the list of ones")
    public static Ingredients getRandomIngredients() {
        List<String> currentList = getAllIngredients();
        List<String> newList = new ArrayList<>();
        Random randomizer = new Random();
        String ingredientId1 = currentList.get(randomizer.nextInt(currentList.size()));
        String ingredientId2 = currentList.get(randomizer.nextInt(currentList.size()));
        String ingredientId3 = currentList.get(randomizer.nextInt(currentList.size()));
        newList.add(ingredientId1);
        newList.add(ingredientId2);
        newList.add(ingredientId3);
        return new Ingredients(newList);
    }

    @Step("Send GET-request /api/ingredients")
    private static List<String> getAllIngredients() {
        return given()
                .get(INGREDIENTS_METHOD)
                .as(IngredientResponse.class)
                .getData()
                .stream()
                .map(Ingredient::get_id)
                .collect(Collectors.toList());
    }

}
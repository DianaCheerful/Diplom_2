package constant;

import model.request.User;

public class TestConstants {

    public static final String STELLAR_BURGER_URL = "https://stellarburgers.nomoreparties.site";

    public static final String EMPTY_STRING = "";
    public static final String JSON_TYPE = "application/json";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String CREATE_USER_METHOD = "/api/auth/register";
    public static final String EDIT_USER_METHOD = "/api/auth/user";
    public static final String LOGIN_USER_METHOD = "/api/auth/login";
    public static final String ORDER_METHOD = "/api/orders";
    public static final String INGREDIENTS_METHOD = "/api/ingredients";
    public static final String RESPONSE_SUCCESS_FIELD = "success";
    public static final String RESPONSE_MESSAGE_FIELD = "message";
    public static final String ORDER_NAME_FIELD = "name";
    public static final String ORDER_FIELD = "order";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "User already exists";
    public static final String EMPTY_REQUIRED_FIELDS_MESSAGE = "Email, password and name are required fields";
    public static final String INCORRECT_EMAIL_OR_PASSWORD_MESSAGE = "email or password are incorrect";
    public static final String EMPTY_TOKEN_MESSAGE = "You should be authorised";
    public static final String EMPTY_INGREDIENTS_MESSAGE = "Ingredient ids must be provided";
    public static final String WRONG_INGREDIENTS_IDS_MESSAGE = "One or more ids provided are incorrect";
    public static final User USER_SAMPLE_DATA = new User("nobody@rr.cog", "froh76", "Drake");
    public static final String SAMPLE_INGREDIENT_ID = "61c0c5a75d1f82901bdaaa6d";

}

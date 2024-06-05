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
    public static final String RESPONSE_SUCCESS_FIELD = "success";
    public static final String RESPONSE_MESSAGE_FIELD = "message";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "User already exists";
    public static final String EMPTY_REQUIRED_FIELDS_MESSAGE = "Email, password and name are required fields";
    public static final String INCORRECT_EMAIL_OR_PASSWORD_MESSAGE = "email or password are incorrect";
    public static final String EMPTY_TOKEN_MESSAGE = "You should be authorised";
    public static final User USER_EDIT_DATA = new User("nobody@rr.cog", "froh76", "Drake");

}

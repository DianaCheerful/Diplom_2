package test;

import constant.UserData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.request.User;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized;

import static constant.TestConstants.STELLAR_BURGER_URL;

public class BaseTest {

    protected User user;
    protected static Response createdResponse;

    public BaseTest(User user) {
        this.user = user;
    }

    public BaseTest() {

    }

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = STELLAR_BURGER_URL;
    }

    @Parameterized.Parameters
    public static Object[][] getUser() {
        return new Object[][]{
                {new User(UserData.USER_1.getEmail(), UserData.USER_1.getPassword(), UserData.USER_1.getName())},
                {new User(UserData.USER_2.getEmail(), UserData.USER_2.getPassword(), UserData.USER_2.getName())},
                {new User(UserData.USER_3.getEmail(), UserData.USER_3.getPassword(), UserData.USER_3.getName())}
        };
    }
}
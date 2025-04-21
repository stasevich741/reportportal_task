package api;

import helpers.constants.BaseConstants;
import helpers.listener.CustomTpl;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

import static helpers.constants.BaseConstants.BASE_URL;
import static io.restassured.RestAssured.given;

public class BaseApiTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(new RequestLoggingFilter()
                , new ResponseLoggingFilter()
                , CustomTpl.customLogFilter().withTemplates());
    }


    protected static String getAccessToken() {
        return given()
                .auth().preemptive().basic("ui", "uiman")
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "password")
                .formParam("username", BaseConstants.LOGIN)
                .formParam("password", BaseConstants.PASSWORD)
                .post(BaseConstants.BASE_URL + "/uat/sso/oauth/token")
                .then()
                .statusCode(200)
                .extract()
                .path("access_token");
    }
}



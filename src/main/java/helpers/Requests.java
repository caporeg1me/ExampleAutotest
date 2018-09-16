package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.Rates;

import java.io.IOException;

import static io.restassured.RestAssured.get;

public class Requests {
    private static final String API_ALL = "/api/v3/stock/all";

    @Step("GET: api rates")
    public static Rates getRates() throws IOException {
        Response response = get(API_ALL);
        response.then().statusCode(200);
        return (Rates) JsonConverter.jsonToObject(response.asString(), Rates.class);
    }
}

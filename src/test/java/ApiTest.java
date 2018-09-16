import helpers.Requests;
import helpers.RunnerExtension;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pojo.Rates;

import java.io.IOException;

import static io.restassured.RestAssured.get;

@Tag("all")
@Tag("api")
@DisplayName("Пример сюита Api")
@ExtendWith(RunnerExtension.class)
class ApiTest {

    @Test
    @DisplayName("Проверка текущего курса доллара 1")
    @Description("Простой тест")
    void currencyRates() {
        //RestAssured.baseURI = System.getProperty("serverUrl");
        Response response = get("/api/v3/stock/all");
        response.then().statusCode(200);
        float current = response.jsonPath().get("usd.current");
        Assertions.assertEquals(68.01 , current);
    }

    @Test
    @DisplayName("Проверка текущего курса доллара 2")
    @Description("Тест с объектами")
    void currencyRatesObj() throws IOException {
        //RestAssured.baseURI = System.getProperty("serverUrl");
        Rates rates = Requests.getRates();
        Assertions.assertEquals(68.01, rates.getUsd().getCurrent());
    }
}

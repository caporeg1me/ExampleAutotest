package helpers;

import com.automation.remarks.video.recorder.VideoRecorder;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.awaitility.core.ConditionTimeoutException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static helpers.AllureActions.*;
import static io.restassured.filter.log.LogDetail.METHOD;
import static io.restassured.filter.log.LogDetail.URI;
import static org.awaitility.Awaitility.await;

public class RunnerExtension implements AfterTestExecutionCallback, BeforeTestExecutionCallback, BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        RestAssured.baseURI = System.getProperty("serverUrl");

        RestAssured.filters(
                new RequestLoggingFilter(URI),
                new RequestLoggingFilter(METHOD),
                new ResponseLoggingFilter(Matchers.not(200)));

        RestAssured.filters(new AllureRestAssured());

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        AllureActions.appendEnvironment();
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {

    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Boolean testResultFailed = context.getExecutionException().isPresent();

        if (WebDriverRunner.hasWebDriverStarted()) {
            String url = WebDriverRunner.url();
            if (url != null) attachUrl(url);

            if (testResultFailed) {
                File screenshot = Screenshots.getLastScreenshot();
                if (screenshot != null) attachScreenshot(screenshot);

                attachConsoleOutput();

                try {
                    File video = VideoRecorder.getLastRecording();
                    await().atMost(5, TimeUnit.SECONDS)
                            .pollDelay(1, TimeUnit.SECONDS)
                            .ignoreExceptions()
                            .until(() -> video != null);

                    if (video != null && video.exists()) attachVideo(video);

                }
                catch (ConditionTimeoutException exception) {
                    System.out.println("VideoRecorder: "+exception.toString());
                }
            }
        }
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {

    }
}

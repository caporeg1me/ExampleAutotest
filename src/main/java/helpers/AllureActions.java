package helpers;

import com.automation.remarks.video.recorder.VideoRecorder;
import com.codeborne.selenide.Selenide;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.logging.LogType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;


class AllureActions {

    @Attachment(value = "Page url")
    static byte[] attachUrl(String url) {
        System.out.println(url);
        return url.getBytes();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    static byte[] attachScreenshot(File screenshot) throws IOException {
        if (screenshot != null) {
            return Files.toByteArray(screenshot);
        }
        else return null;
    }

    @Attachment(value = "Recorded video", type = "video/mp4")
    static byte[] attachVideo(File video) throws IOException {

        if (VideoRecorder.conf().videoEnabled() && video.exists()) {
            byte[] file =  Files.toByteArray(video);
            System.out.println(video.getAbsolutePath());
            return file;
        }
        else return null;
    }

    @Attachment(value = "Browser console")
    static String attachConsoleOutput() {
        List logs = Selenide.getWebDriverLogs(LogType.BROWSER, Level.SEVERE);
        return logs.toString();
    }

    static void appendEnvironment() {
        try {
            FileInputStream in = new FileInputStream("allure-results/environment.properties");
            Properties properties = new Properties();
            properties.load(new InputStreamReader(in, StandardCharsets.UTF_8));
            properties.setProperty("serverUrl", System.getProperty("serverUrl"));
            properties.setProperty("includeTags", System.getProperty("includeTags"));
            properties.setProperty("excludeTags", System.getProperty("excludeTags"));
            properties.setProperty("browser", System.getProperty("browser"));
            in.close();

            FileOutputStream out = new FileOutputStream("allure-results/environment.properties");
            properties.store(out, null);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

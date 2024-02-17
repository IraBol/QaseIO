package utils;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class AllureUtils {

    //метод со скриншотами не работает c версией testng 7.8.0 - пришлось понизить до 7.4.0
    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        log.info("Take Screenshot");
        if (getWebDriver() != null) {
            return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        } else {
            throw new NullPointerException("Driver is null, screenshot can't be taken");
        }
    }
}

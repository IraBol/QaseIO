package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import utils.PropertyReader;

@Log4j2
public abstract class BasePage {

    final String BASE_URL = PropertyReader.getProperty("qase.base.url");

    public abstract BasePage openPage(String path);

    public abstract BasePage isPageOpened();

    protected void waitForPageLoaded() {
        log.info("Wait for page content to be loaded");
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }
}

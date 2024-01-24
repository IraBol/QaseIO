package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
@Log4j2
public class LoginPage extends BasePage {

   private final String EMAIL_NAME = "email";
   private final String PASS_NAME = "password";
   private final String SUBMIT_BTN_CSS = "[type='submit']";
   private final String USER_ICON_BUTTON_XPATH = "//img[@alt='Ira Bol']";
   private final String SIGN_OUT_BUTTON_XPATH = "//span[text()='Sign out']";
   private final String MISSING_EMAIL_ERROR_MESSAGE_XPATH = "//input[@name='email']/..//following-sibling::*[text()='This field is required']";
   private final String MISSING_PASS_ERROR_MESSAGE_XPATH = "//input[@name='password']/..//following-sibling::*[text()='This field is required']";



    @Override
    @Step("Open login page")
    public LoginPage openPage() {
        log.info("Open login page");
        open(BASE_URL + "/login");
        waitForPageLoaded();
        return this;
    }

    @Override
    @Step("Verify whether login page is opened")
    public LoginPage isPageOpened() {
        log.info("Verify whether login page is opened");
        try {
            $(By.name(EMAIL_NAME)).shouldBe(Condition.visible);
            $(By.name(PASS_NAME)).shouldBe(Condition.visible);
        } catch (Exception e) {
          log.error("Login page is not opened");
        }
        return this;
    }

    @Step("Fill out login form")
    public LoginPage fillOutLoginForm(String username, String password) {
        log.info("Fill out login form");
        $(By.name(EMAIL_NAME)).setValue(username);
        $(By.name(PASS_NAME)).setValue(password);
        return this;
    }

    @Step("Click submit button")
    public void clickSubmitButton() {
        log.info("Click submit button");
        $(By.cssSelector(SUBMIT_BTN_CSS)).click();
    }

    @Step("Sign out from the system")
    public LoginPage signOut() {
        log.info("Sign out from the system");
        $(By.xpath(USER_ICON_BUTTON_XPATH)).click();
        $(By.xpath(SIGN_OUT_BUTTON_XPATH)).shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
        return this;
    }

    @Step("Verify message for missing email")
    public LoginPage waitTillMissingEmailErrorAppears() {
        log.info("Verify message for missing email");
        $(By.xpath(MISSING_EMAIL_ERROR_MESSAGE_XPATH)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Verify message for missing password")
    public LoginPage waitTillMissingPasswordErrorAppears() {
        log.info("Verify message for missing password");
        $(By.xpath(MISSING_PASS_ERROR_MESSAGE_XPATH)).shouldBe(Condition.visible);
        return this;
    }
}

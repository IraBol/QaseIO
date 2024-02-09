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
    private final String SIGN_IN_BTN_CSS = "[type='submit']";
    private final String USER_ICON_BUTTON_XPATH = "//img[@alt='Ira Bol']";
    private final String SIGN_OUT_BUTTON_XPATH = "//span[text()='Sign out']";
    private final String MISSING_EMAIL_ERROR_MESSAGE_XPATH = "//input[@name='email']/..//following-sibling::*[text()='This field is required']";
    private final String MISSING_PASS_ERROR_MESSAGE_XPATH = "//input[@name='password']/..//following-sibling::*[text()='This field is required']";
    private static final String CREDENTIALS_MISMATCH_ERROR_MESSAGE_XPATH = "//span[text()='These credentials do not match our records.']";
    private static final String PASSWORD_APPEARED_TO_BE_DATA_LEAK_ERROR_MESSAGE_XPATH = "//a[text()='public data leak']";

    @Override
    @Step("Open login page")
    public LoginPage openPage(String path) {
        log.info("Open login page");
        open(String.format(BASE_URL + "%s", path));
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

    @Step("Click sign in button")
    public LoginPage clickSignInButton() {
        log.info("Click submit button");
        $(By.cssSelector(SIGN_IN_BTN_CSS)).click();
        return this;
    }

    @Step("Sign out from the system")
    public LoginPage signOut() {
        log.info("Sign out from the system");
        $(By.xpath(USER_ICON_BUTTON_XPATH)).click();
        $(By.xpath(SIGN_OUT_BUTTON_XPATH)).shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
        return this;
    }

    @Step("Validate missing email message")
    public LoginPage waitTillMissingEmailErrorAppears() {
        log.info("Validate missing email message");
        $(By.xpath(MISSING_EMAIL_ERROR_MESSAGE_XPATH)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Validate missing password message")
    public LoginPage waitTillMissingPasswordErrorAppears() {
        log.info("Validate missing password message");
        $(By.xpath(MISSING_PASS_ERROR_MESSAGE_XPATH)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Validate mismatching credentials message")
    public LoginPage waitTillCredentialsMismatchErrorAppears() {
        log.info("Validate mismatching credentials message");
        $(By.xpath(CREDENTIALS_MISMATCH_ERROR_MESSAGE_XPATH)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Validate data leak password message")
    public LoginPage waitTillDataLeakPasswordErrorAppears() {
        log.info("Validate data leak password message");
        $(By.xpath(PASSWORD_APPEARED_TO_BE_DATA_LEAK_ERROR_MESSAGE_XPATH)).shouldBe(Condition.visible);
        return this;
    }
}

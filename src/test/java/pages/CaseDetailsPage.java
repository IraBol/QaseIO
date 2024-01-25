package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class CaseDetailsPage extends BasePage {

    private static final String GENERAL_BUTTON_XPATH = "//button[text()='General']";
    private static final String PROPERTIES_BUTTON_XPATH = "//button[text()='Properties']";
    //переделать через DTO!!!
    private static final String TITLE_FIELD_XPATH = "//div[text()='Diploma Test Case Title']";
    private static final String DESCRIPTION_XPATH = "//label[text()='Description']";
    private static final String PRE_CONDITIONS_XPATH = "//label[text()='Pre-conditions']";
    private static final String POST_CONDITIONS_XPATH = "//label[text()='Post-conditions']";
    private static final String ATTACHMENTS_XPATH = "//h3[text()='Attachments']/..//span";
    private static final String PARAMETERS_TITLE_XPATH = "//h3[text()='Parameters']/../div/div/div";
    private static final String PARAMETERS_VALUE_XPATH = "//h3[text()='Parameters']/..//following::li";
    private static final String STEPS_XPATH = "//h3[text()='Steps']/..//following::td/span";
//h3[text()='Steps']/..//following::td/

    @Override
    @Step("Open test case details page")
    public CaseDetailsPage openPage(String path) {
        log.info("Open test case details page");
        open(String.format(BASE_URL + "%s", path));
        waitForPageLoaded();
        return this;
    }

    @Override
    @Step("Verify whether test case details page is opened")
    public CaseDetailsPage isPageOpened() {
        log.info("Verify whether test case details page opened");
        try {
            $(By.xpath(GENERAL_BUTTON_XPATH)).shouldBe(Condition.visible);
            $(By.xpath(PROPERTIES_BUTTON_XPATH)).shouldBe(Condition.visible);
        } catch (Exception e) {
            log.error("Test case details page is not opened");
        }
        return this;
    }

    public CaseDetailsPage clickGeneralButton() {
        $(By.xpath(GENERAL_BUTTON_XPATH)).click();
        return this;
    }

    public CaseDetailsPage clickPropertiesButton() {
        $(By.xpath(PROPERTIES_BUTTON_XPATH)).click();
        return this;
    }

    public CaseDetailsPage validateGeneralTabInfo() {

        return this;
    }

    public CaseDetailsPage validatePropertiesTabInfo() {

        return this;
    }

    public void editSomeFieldsFromTestCaseForm() {

    }
}

package pages;

import com.codeborne.selenide.Condition;
import dto.Case;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import wrappers.CheckBox;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class CaseDetailsPage extends BasePage {

    private static final String GENERAL_BUTTON_XPATH = "//button[text()='General']";
    private static final String PROPERTIES_BUTTON_XPATH = "//button[text()='Properties']";
    private static final String TC_DETAILS_TITLE_XPATH = "//h1/div[text()='%s']";
    private static final String TC_DETAILS_LOADED_ATTACHMENT_XPATH = "//h3[text()='%s']/..//*[text()='%s']/ancestor::a";
    private static final String TC_DETAILS_GENERAL_XPATH = "//%s[text()='%s']/..//*[text()='%s']";

    @Override
    @Step("Open test case details page")
    public CaseDetailsPage openPage(String path) {
        log.info("Open test case details page");
        open(String.format(BASE_URL + "/case/%s-1", path));
        waitForPageLoaded();
        return this;
    }

    @Override
    @Step("Verify whether test case details page is opened")
    public CaseDetailsPage isPageOpened() {
        log.info("Verify whether test case details page is opened");
        try {
            $(By.xpath(GENERAL_BUTTON_XPATH)).shouldBe(Condition.visible);
            $(By.xpath(PROPERTIES_BUTTON_XPATH)).shouldBe(Condition.visible);
        } catch (Exception e) {
            log.error("Test case details page is not opened");
        }
        return this;
    }

    public void validateTestCaseDetailsTitle(String text) {
        log.info("Test case title is'{}'", text);
        if (text != null) {
            $(By.xpath(String.format(TC_DETAILS_TITLE_XPATH, text))).shouldBe(Condition.visible);
        }
    }

    @Step("Open properties tab")
    public CaseDetailsPage openPropertiesTab() {
        log.info("Open properties tab");
        $(By.xpath(PROPERTIES_BUTTON_XPATH)).click();
        return this;
    }

    //method moved here from wrappers package/class
    public void validateGeneralTabField(String tag, String value, String text) {
        log.info("'{}' field contains '{}' text", value, text);
        if (text != null) {
            $(By.xpath(String.format(TC_DETAILS_GENERAL_XPATH, tag, value, text))).shouldBe(Condition.visible);
        }
    }

    //method moved here from wrappers package/class
    public void validateUploadedAttachment(String value, String attachmentTitle) {
        log.info("'{}' field contains '{}' title", value, attachmentTitle);
        if (attachmentTitle != null) {
            $(By.xpath(String.format(TC_DETAILS_LOADED_ATTACHMENT_XPATH, value, attachmentTitle))).shouldBe(Condition.visible);
        }
    }

    @Step("Validate general tab info")
    public CaseDetailsPage validateGeneralTabInfo(Case testCase) {
        log.info("Validate general tab info '{}'", testCase);

        validateTestCaseDetailsTitle(testCase.getTitle());
        validateGeneralTabField("label", "Description", testCase.getDescription());
        validateGeneralTabField("label", "Pre-conditions", testCase.getPreConditions());
        validateGeneralTabField("label", "Post-conditions", testCase.getPostConditions());
        validateUploadedAttachment("Attachments", testCase.getAttachmentTitle());
        validateGeneralTabField("h3", "Parameters", testCase.getParameterTitle());
        validateGeneralTabField("h3", "Parameters", testCase.getParameterValue());
        validateGeneralTabField("h3", "Steps", testCase.getGherkinStepsInput());

        return this;
    }

    //method moved here from wrappers package/class
    public void validatePropertiesTabField(String tag, String value, String text) {
        log.info("'{}' field contains '{}' text", value, text);
        if (text != null) {
            $(By.xpath(String.format(TC_DETAILS_GENERAL_XPATH, tag, value, text))).shouldBe(Condition.visible);
        }
    }

    @Step("Validate properties tab info")
    public CaseDetailsPage validatePropertiesTabInfo(Case testCase) {
        log.info("Validate properties tab info '{}'", testCase);

        validatePropertiesTabField("label", "Severity", testCase.getSeverity());
        validatePropertiesTabField("label", "Status", testCase.getStatus());
        validatePropertiesTabField("label", "Priority", testCase.getPriority());
        validatePropertiesTabField("label", "Behavior", testCase.getBehavior());
        validatePropertiesTabField("label", "Type", testCase.getType());
        validatePropertiesTabField("label", "Is flaky", testCase.getIsFlaky());
        validatePropertiesTabField("span", "Milestone", testCase.getMilestone());
        validatePropertiesTabField("label", "Layer", testCase.getLayer());
        validatePropertiesTabField("label", "Automation status", testCase.getAutomationStatus());
        new CheckBox().validatePropertiesTabCheckBoxStatus("To be automated", testCase.isPropertiesTabCheckBoxChecked());

        return this;
    }
}

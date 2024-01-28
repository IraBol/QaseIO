package pages;

import com.codeborne.selenide.Condition;
import dto.Case;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import wrappers.CheckBox;
import wrappers.Input;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class CaseDetailsPage extends BasePage {

    private static final String GENERAL_BUTTON_XPATH = "//button[text()='General']";
    private static final String PROPERTIES_BUTTON_XPATH = "//button[text()='Properties']";
    private static final String TC_TITLE_ON_PROJECT_DETAILS_PAGE_XPATH = "//div[text()='%s']";

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

    @Step("Open relevant test case")
    public CaseDetailsPage openTestCase(String testCaseTitle) {
        log.info("Open relevant test case");
        $(By.xpath(String.format(TC_TITLE_ON_PROJECT_DETAILS_PAGE_XPATH, testCaseTitle))).shouldBe(Condition.visible, Duration.ofSeconds(10)).
                click();
        waitForPageLoaded();
        return this;
    }

    @Step("Open properties tab")
    public CaseDetailsPage openPropertiesTab() {
        log.info("Open properties tab");
        $(By.xpath(PROPERTIES_BUTTON_XPATH)).click();
        return this;
    }

    @Step("Validate general tab info")
    public CaseDetailsPage validateGeneralTabInfo(Case testCase) {
        log.info("Validate general tab info");

        new Input().validateTestCaseDetailsTitle(testCase.getTitle());
        new Input().validateGeneralTabFields("label", "Description", testCase.getDescription());
        new Input().validateGeneralTabFields("label", "Pre-conditions", testCase.getPreConditions());
        new Input().validateGeneralTabFields("label", "Post-conditions", testCase.getPostConditions());
        new Input().validateUploadedAttachment("Attachments", testCase.getAttachmentTitle());
        new Input().validateGeneralTabFields("h3", "Parameters", testCase.getParameterTitle());
        new Input().validateGeneralTabFields("h3", "Parameters", testCase.getParameterValue());
        new Input().validateGeneralTabFields("h3", "Steps", testCase.getGherkinStepsInput());
        return this;
    }
    @Step("Validate properties tab info")
    public CaseDetailsPage validatePropertiesTabInfo(Case testCase) {
        log.info("Validate properties tab info");

        new Input().validateGeneralTabFields("label", "Severity", testCase.getSeverity());
        new Input().validateGeneralTabFields("label", "Status", testCase.getStatus());
        new Input().validateGeneralTabFields("label", "Priority", testCase.getPriority());
        new Input().validateGeneralTabFields("label", "Behavior", testCase.getBehavior());
        new Input().validateGeneralTabFields("label", "Type", testCase.getType());
        new Input().validateGeneralTabFields("label", "Is flaky", testCase.getIsFlaky());
        new Input().validateGeneralTabFields("span", "Milestone", testCase.getMilestone());
        //tags - will be later
        new Input().validateGeneralTabFields("label", "Layer", testCase.getLayer());
        new Input().validateGeneralTabFields("label", "Automation status", testCase.getAutomationStatus());
        new CheckBox().validateCheckBoxStatus("To be automated", testCase.isCheckBoxChecked());
        return this;
    }
}

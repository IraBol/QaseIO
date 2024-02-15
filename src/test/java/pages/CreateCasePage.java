/*
Verification of placeholder

@Step("Validate 'Title' field placeholder text")
    public CreateCasePage titleFieldPlaceholderTextShouldBeVisible() {
        log.info("Validate 'Title' field placeholder text");
        $(By.id(TC_TITLE_FIELD_ID)).shouldHave(attribute("placeholder", "For example: Authorization"));
        return this;
    }
 */
package pages;

import com.codeborne.selenide.Condition;
import dto.Case;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;
import wrappers.CheckBox;
import wrappers.Dropdown;
import wrappers.Input;
import wrappers.TextArea;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class CreateCasePage extends BasePage {

    private static final String TC_SAVE_BUTTON_ID = "save-case";
    private static final String TC_SAVE_AND_CREATE_ANOTHER_BUTTON_XPATH = "//button/span[text()='Save and create another']";
    private static final String TC_CANCEL_BUTTON_XPATH = "//button/span[text()='Cancel']";
    private static final String MODAL_WINDOW_CLOSE_FORM_HEADER = "//h3[text()='Close form?']";
    private static final String MODAL_WINDOW_CLOSE_FORM_BUTTON_XPATH = "//button/span[text()='Close form']";
    private static final String MODAL_WINDOW_CROSS_BUTTON_XPATH = "//h3[text()='Close form?']/../preceding-sibling::button";
    private static final String MODAL_WINDOW_CANCEL_BUTTON_XPATH = "//*[text()='Close form?']/following::span[text()='Cancel']";
    private static final String TC_TITLE_FIELD_ID = "title";
    private static final String TC_INPUT_FILE_XPATH = "//div[@data-react-modal-body-trap][2]/following-sibling::input[@type='file']";
    private final String TC_MILESTONE_DROPDOWN_XPATH = "//div[@id='milestoneGroup']//following::div";
    private final String TC_MILESTONE_DROPDOWN_OPTION_XPATH = "//div[@id='modals']/following-sibling::div//*[text()='%s']";
    private final String TC_SUITE_DROPDOWN_XPATH = "//label[text()='Suite']/following-sibling::div";
    private final String TC_SUITE_DROPDOWN_OPTION_XPATH = "//label[text()='Suite']/following-sibling::div//*[text()='%s']";
    private final String TC_STEPS_DROPDOWN_XPATH = "//div[text()='Test Case Steps']/div";
    private final String TC_STEPS_DROPDOWN_OPTION_XPATH = "//div[text()='Test Case Steps']/div//*[text()='%s']";
    private static final String TC_GHERKIN_ADD_STEP_BUTTON_ID = "gherkin-add-step-btn";
    private static final String TC_GHERKIN_STEPS_DROPDOWN_XPATH = "//div[text()='%s']/following::tr//div[text()='%s']/following::div";
    private static final String TC_GHERKIN_STEPS_DROPDOWN_OPTION_XPATH = "//div[text()='%s']/following::tr//div[text()='%s']/following::div[text()='%s']";
    private static final String TC_ADD_PARAMETER_BUTTON_XPATH = "//button/span[text()='Add parameter']";
    private static final String TC_PARAMETER_INPUT_XPATH = "//label[text()='%s']/following::input";
    private static final String TC_GHERKIN_INPUT_XPATH = "//div[text()='%s']/following::tr//div[text()='%s']/following::input[3]";
    private static final String TC_ADD_ATTACHMENT_BUTTON_XPATH = "//button[text()='Add attachment']";
    private static final String TC_SUCCESSFUL_CREATION_FLASH_MESSAGE_XPATH = "//script[@id='flashMessages']/following::" +
            "div[@id='layout']//*[text()='%s']";
    private static final String TC_TITLE_ON_PROJECT_DETAILS_PAGE_XPATH = "//div[text()='%s']";

    @Override
    @Step("Open create test case page")
    public CreateCasePage openPage(String path) {
        log.info("Open create test case page");
        open(String.format(BASE_URL + "%s", path));
        waitForPageLoaded();
        return this;
    }

    @Override
    @Step("Verify whether create test case page is opened")
    public CreateCasePage isPageOpened() {
        log.info("Verify whether create case page is opened");
        try {
            $(By.id(TC_SAVE_BUTTON_ID)).shouldBe(Condition.visible);
        } catch (Exception e) {
            log.error("Create case page is not opened");
        }
        return this;
    }

    @Step("Validate 'Title' field validation message")
    public CreateCasePage requiredFieldValidationMessageShouldBeVisible(String requiredFieldMessage) {
        log.info("'Title' field validation message is: '{}'", requiredFieldMessage);
        String message = $(By.id(TC_TITLE_FIELD_ID)).getAttribute("validationMessage");
        Assert.assertEquals(message, requiredFieldMessage);
        return this;
    }

    //method moved here from wrappers package/class
    public void uploadFile(File file) {
        log.info("Add attachment '{}'", file);
        if (file != null) {
            $(By.xpath(TC_ADD_ATTACHMENT_BUTTON_XPATH)).click();
            $(By.xpath(TC_INPUT_FILE_XPATH)).uploadFile(file);
        }
    }

    //method moved here from wrappers package/class
    public void setMilestoneDropdownValue(String option) {
        log.info("Select '{}' option inside milestone dropdown", option);
        if (option != null) {
            $(By.xpath(String.format(TC_MILESTONE_DROPDOWN_XPATH))).click();
            $(By.xpath(String.format(TC_MILESTONE_DROPDOWN_OPTION_XPATH, option))).click();
        }
    }

    //method moved here from wrappers package/class
    public void setSuiteDropdownValue(String option) {
        log.info("Select '{}' option inside suite dropdown", option);
        if (option != null) {
            $(By.xpath(String.format(TC_SUITE_DROPDOWN_XPATH))).click();
            $(By.xpath(String.format(TC_SUITE_DROPDOWN_OPTION_XPATH, option))).click();
        }
    }

    //method moved here from wrappers package/class
    public void setTestCaseStepsDropdownValue(String option) {
        log.info("Select '{}' option inside test case steps dropdown", option);
        if (option != null) {
            $(By.xpath(String.format(TC_STEPS_DROPDOWN_XPATH))).click();
            $(By.xpath(String.format(TC_STEPS_DROPDOWN_OPTION_XPATH, option))).click();
        }
    }

    //method moved here from wrappers package/class
    public void setGherkinStepsDropdownValue(String label, String inputNumber, String option) {
        log.info("Select '{}' option inside gherkin steps dropdown", option);
        if (option != null) {
            $(By.id(TC_GHERKIN_ADD_STEP_BUTTON_ID)).click();
            $(By.xpath(String.format(TC_GHERKIN_STEPS_DROPDOWN_XPATH, label, inputNumber))).click();
            $(By.xpath(String.format(TC_GHERKIN_STEPS_DROPDOWN_OPTION_XPATH, label, inputNumber, option))).shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        }
    }

    //method moved here from wrappers package/class
    public void writeParameterTitle(String label, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(TC_ADD_PARAMETER_BUTTON_XPATH)).click();
            $(By.xpath(String.format(TC_PARAMETER_INPUT_XPATH, label))).sendKeys(text);
        }
    }

    //method moved here from wrappers package/class
    public void writeParameterValue(String label, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(String.format(TC_PARAMETER_INPUT_XPATH, label))).sendKeys(text);
        }
    }

    //method moved here from wrappers package/class
    public void writeGherkinSteps(String label, String inputNumber, String text) {
        log.info("Write '{}' into gherkin input field", text);
        if (text != null) {
            $(By.xpath(String.format(TC_GHERKIN_INPUT_XPATH, label, inputNumber))).sendKeys(text);
        }
    }

    @Step("Fill out test case form")
    public ProjectDetailsPage fillOutTestCaseForm(Case testCase) {
        log.info("Fill out test case form with values: '{}'", testCase);

        Input input = new Input();
        Dropdown dropdown = new Dropdown();
        TextArea textArea = new TextArea();
        CheckBox checkBox = new CheckBox();

        //Basic
        input.write("Title", "title", testCase.getTitle());
        dropdown.setDropdownValue("Status", testCase.getStatus());
        textArea.write("Description", testCase.getDescription());
        setSuiteDropdownValue(testCase.getSuite());
        dropdown.setDropdownValue("Severity", testCase.getSeverity());
        dropdown.setDropdownValue("Priority", testCase.getPriority());
        dropdown.setDropdownValue("Type", testCase.getType());
        dropdown.setDropdownValue("Layer", testCase.getLayer());
        dropdown.setDropdownValue("Is flaky", testCase.getIsFlaky());
        setMilestoneDropdownValue(testCase.getMilestone());
        dropdown.setDropdownValue("Behavior", testCase.getBehavior());
        dropdown.setDropdownValue("Automation status", testCase.getAutomationStatus());
        checkBox.selectCheckBoxOption("To be automated", testCase.isCheckBoxChecked());
        //Conditions
        input.write("Pre-conditions", "0-preconditions", testCase.getPreConditions());
        input.write("Post-conditions", "0-postconditions", testCase.getPostConditions());
        //Attachments
        uploadFile(testCase.getAddAttachment());
        //Parameters
        writeParameterTitle("Parameter title", testCase.getParameterTitle());
        writeParameterValue("Parameter values", testCase.getParameterValue());
        //Test Case Steps
        setTestCaseStepsDropdownValue(testCase.getTestCaseStepsDropdownOption());
        //Given
        setGherkinStepsDropdownValue("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsDropdownOption());
        writeGherkinSteps("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsInput());

        return new ProjectDetailsPage();
    }

    @Step("Validate successful test case creation message")
    public ProjectDetailsPage waitTillSuccessfulCaseCreationMessageAppears(String successfulCaseCreationMessage) {
        log.info("Successful test case creation message is: '{}'", successfulCaseCreationMessage);
        $(By.xpath(String.format(TC_SUCCESSFUL_CREATION_FLASH_MESSAGE_XPATH, successfulCaseCreationMessage))).shouldBe(Condition.visible);
        return new ProjectDetailsPage();
    }

    @Step("Validate created test case on project details page")
    public ProjectDetailsPage waitTillCaseCreated(String testCaseTitle) {
        log.info("Created test case on project details page title is: '{}'", testCaseTitle);
        $(By.xpath(String.format(TC_TITLE_ON_PROJECT_DETAILS_PAGE_XPATH, testCaseTitle))).shouldBe(Condition.visible, Duration.ofSeconds(20));
        return new ProjectDetailsPage();
    }

    @Step("Click 'Save' button")
    public ProjectDetailsPage clickSaveButton() {
        log.info("Click 'Save' button");
        $(By.id(TC_SAVE_BUTTON_ID)).click();
        return new ProjectDetailsPage();
    }

    @Step("Click 'Save and create another' button")
    public CreateCasePage clickSaveAndCreateAnotherButton() {
        log.info("Click 'Save and create another' button");
        $(By.xpath(TC_SAVE_AND_CREATE_ANOTHER_BUTTON_XPATH)).click();
        return this;
    }

    @Step("Click 'Cancel' button")
    public CreateCasePage clickCancelButton() {
        log.info("Click 'Cancel' button");
        $(By.xpath(TC_CANCEL_BUTTON_XPATH)).click();
        return this;
    }

    //Modal Window methods
    @Step("Validate if 'Close form' modal window appears")
    public CreateCasePage waitTillCloseFormModalWindowAppears() {
        log.info("Wait till 'CloseForm' modal window Appears");
        $(By.xpath(MODAL_WINDOW_CLOSE_FORM_HEADER)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Click 'Close form' modal window button")
    public ProjectDetailsPage clickCloseFormModalWindowButton() {
        log.info("Click 'Close form' modal window button");
        $(By.xpath(MODAL_WINDOW_CLOSE_FORM_BUTTON_XPATH)).click();
        return new ProjectDetailsPage();
    }

    @Step("Click 'Cancel' modal window button")
    public CreateCasePage clickCancelModalWindowButton() {
        log.info("Click 'Cancel' modal window button");
        $(By.xpath(MODAL_WINDOW_CANCEL_BUTTON_XPATH)).click();
        return this;
    }

    @Step("Click 'Cross' modal window button")
    public CreateCasePage clickCrossModalWindowButton() {
        log.info("Click 'Cross' modal window button");
        $(By.xpath(MODAL_WINDOW_CROSS_BUTTON_XPATH)).click();
        return this;
    }
}

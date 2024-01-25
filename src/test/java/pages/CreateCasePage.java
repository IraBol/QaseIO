package pages;

import com.codeborne.selenide.Condition;
import dto.Case;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;
import wrappers.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class CreateCasePage extends BasePage {

    private static final String TC_SAVE_BUTTON_ID = "save-case";
    private static final String TC_SAVE_AND_CREATE_ANOTHER_BUTTON_XPATH = "//button/span[text()='Save and create another']";
    private static final String TC_CANCEL_BUTTON_XPATH = "//button/span[text()='Cancel']";
    private static final String MODAL_WINDOW_CLOSE_FORM_HEADER = "//h3[text()='Close form?']";
    private static final String MODAL_WINDOW_CLOSE_FORM_BUTTON_XPATH = "//button/span[text()='Close form']";
    private static final String MODAL_WINDOW_CROSS_CLOSE_BUTTON_XPATH = "//h3[text()='Close form?']/../preceding-sibling::button";
    private static final String MODAL_WINDOW_CLOSE_FORM_CANCEL_BUTTON_XPATH = "//*[text()='Close form?']/following::span[text()='Cancel']";
    private static final String TC_TITLE_FIELD_ID = "title";
    private static final String TC_SUCCESSFUL_CREATION_FLASH_MESSAGE_XPATH = "//script[@id='flashMessages']/following::div[@id='layout']//*[text()='Test case was created successfully!']";
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

    @Step("Validate 'Title' field placeholder text")
    public CreateCasePage titleFieldPlaceholderTextShouldBeVisible() {
        log.info("Validate 'Title' field placeholder text");
        $(By.id(TC_TITLE_FIELD_ID)).shouldHave(attribute("placeholder", "For example: Authorization"));
        return this;
    }

    @Step("Validate 'Title' field validation message")
    public CreateCasePage requiredFieldValidationMessageShouldBeVisible() {
        log.info("Validate 'Title' field validation message");
        String message = $(By.id(TC_TITLE_FIELD_ID)).getAttribute("validationMessage");
        Assert.assertEquals(message, "Please fill out this field.");
        return this;
    }

    @Step("Fill out test case form")
    public ProjectDetailsPage fillOutTestCaseForm(Case testCase) {
        log.info("Fill out test case form");

        //Basic
        new Input().write("Title", "title", testCase.getTitle());
        new Dropdown().setDropdownValue("Status", testCase.getStatus());
        new TextArea().write("Description", testCase.getDescription());
        new Dropdown().setSuiteDropdownValue(testCase.getSuite());
        new Dropdown().setDropdownValue("Severity", testCase.getSeverity());
        new Dropdown().setDropdownValue("Priority", testCase.getPriority());
        new Dropdown().setDropdownValue("Type", testCase.getType());
        new Dropdown().setDropdownValue("Layer", testCase.getLayer());
        new Dropdown().setDropdownValue("Is flaky", testCase.getIsFlaky());
        new Dropdown().setMilestoneDropdownValue(testCase.getMilestone());
        new Dropdown().setDropdownValue("Behavior", testCase.getBehavior());
        new Dropdown().setDropdownValue("Automation status", testCase.getAutomationStatus());
        new CheckBox().selectCheckBoxOption("To be automated", "ToBeAutomated", testCase.isCheckBoxChecked());
        //Conditions
        new Input().write("Pre-conditions", "0-preconditions", testCase.getPreConditions());
        new Input().write("Post-conditions", "0-postconditions", testCase.getPostConditions());
        //Tags
//      new Dropdown().setTagsDropdownValue("Tags", testCase.getTags());
        //Attachments
        new Input().uploadFile(testCase.getAddAttachment());
        //Parameters
        new Input().writeParameterTitle("Parameter title", testCase.getParameterTitle());
        new Input().writeParameterValue("Parameter values", testCase.getParameterValue());
        //Test Case Steps
        new Dropdown().setTestCaseStepsDropdownValue(testCase.getTestCaseStepsDropdownOption());
        //Given
        new Dropdown().setGherkinStepsDropdownValue("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsDropdownOption());
        new Input().writeGherkinSteps("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsInput());
//        //When
//        new Dropdown().setGherkinStepsDropdownValue("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsDropdownOption());
//        new Input().writeGherkinSteps("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsInput());
//        //Then
//        new Dropdown().setGherkinStepsDropdownValue("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsDropdownOption());
//        new Input().writeGherkinSteps("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsInput());
        return new ProjectDetailsPage();
    }

    @Step("Validate successful test case creation message")
    public ProjectDetailsPage waitTillSuccessfulCaseCreationMessageAppears() {
        log.info("Validate successful test case creation message");
        $(By.xpath(TC_SUCCESSFUL_CREATION_FLASH_MESSAGE_XPATH)).shouldBe(Condition.visible);
        return new ProjectDetailsPage();
    }

    @Step("Validate created test case on project details page")
    public ProjectDetailsPage waitTillCaseCreated(String testCaseTitle) {
        log.info("Validate created test case on project details page");
        $(By.xpath(String.format(TC_TITLE_ON_PROJECT_DETAILS_PAGE_XPATH, testCaseTitle))).shouldBe(Condition.visible);
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
        log.info("");
        $(By.xpath(TC_SAVE_AND_CREATE_ANOTHER_BUTTON_XPATH)).click();
        return this;
    }

    @Step("Click 'Cancel' button")
    public CreateCasePage clickCancelButton() {
        log.info("");
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
        $(By.xpath(MODAL_WINDOW_CLOSE_FORM_CANCEL_BUTTON_XPATH)).click();
        return this;
    }

    @Step("Click 'Cross' modal window button")
    public CreateCasePage clickCrossModalWindowButton() {
        log.info("Click 'Cross' modal window button");
        $(By.xpath(MODAL_WINDOW_CROSS_CLOSE_BUTTON_XPATH)).click();
        return this;
    }


}

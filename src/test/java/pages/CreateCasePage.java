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

    public CreateCasePage openPage() {
        return this;
    }

    @Step("Verify whether create case page opened")
    public CreateCasePage isPageOpened() {
        log.info("Verify whether create case page opened");
        try {
            $(By.id(TC_SAVE_BUTTON_ID)).shouldBe(Condition.visible);
        } catch (Exception e) {
            log.error("Create case page is not opened");
        }
        return this;
    }

    public CreateCasePage titleFieldPlaceholderTextShouldBeVisible() {
        $(By.id(TC_TITLE_FIELD_ID)).shouldHave(attribute("placeholder", "For example: Authorization"));
        return this;
    }

    public CreateCasePage fieldValidationMessageShouldBeVisible() {
        String message = $(By.id(TC_TITLE_FIELD_ID)).getAttribute("validationMessage");
        Assert.assertEquals(message, "Please fill out this field.");
        return this;
    }

    public ProjectDetailsPage fillOutTestCaseForm(Case testCase) {
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
        new Dropdown().setGherkinStepsDropdownValue("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsDropdownOption());
        new Input().writeGherkinSteps("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsInput());
        //Then
        new Dropdown().setGherkinStepsDropdownValue("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsDropdownOption());
        new Input().writeGherkinSteps("Test Case Steps", testCase.getGherkinStepsNumber(), testCase.getGherkinStepsInput());
        return new ProjectDetailsPage();
    }

    public ProjectDetailsPage waitTillSuccessfulCaseCreationMessageAppears() {
        $(By.xpath(TC_SUCCESSFUL_CREATION_FLASH_MESSAGE_XPATH)).shouldBe(Condition.visible);
        return new ProjectDetailsPage();
    }

    public void editSomeFieldsFromTestCaseForm() {

    }

    public ProjectDetailsPage clickSaveButton() {
        $(By.id(TC_SAVE_BUTTON_ID)).click();
        return new ProjectDetailsPage();
    }

    public CreateCasePage clickSaveAndCreateAnotherButton() {
        $(By.xpath(TC_SAVE_AND_CREATE_ANOTHER_BUTTON_XPATH)).click();
        return this;
    }

    public CreateCasePage clickCancelButton() {
        $(By.xpath(TC_CANCEL_BUTTON_XPATH)).click();
        return this;
    }

    //Modal Window methods
    public CreateCasePage waitTillModalCloseFormWindowAppears() {
        $(By.xpath(MODAL_WINDOW_CLOSE_FORM_HEADER)).shouldBe(Condition.visible);
        return this;
    }

    public ProjectDetailsPage clickCloseFormButton() {
        $(By.xpath(MODAL_WINDOW_CLOSE_FORM_BUTTON_XPATH)).click();
        return new ProjectDetailsPage();
    }

    public CreateCasePage clickCloseFormCancelButton() {
        $(By.xpath(MODAL_WINDOW_CLOSE_FORM_CANCEL_BUTTON_XPATH)).click();
        return this;
    }

    public CreateCasePage clickCloseFormCrossCancelButton() {
        $(By.xpath(MODAL_WINDOW_CROSS_CLOSE_BUTTON_XPATH)).click();
        return this;
    }


}

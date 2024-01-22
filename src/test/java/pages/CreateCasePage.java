package pages;

import com.codeborne.selenide.Condition;
import dto.Case;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;
import wrappers.*;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

@Log4j2
public class CreateCasePage extends BasePage {

    private static final String SAVE_BUTTON_ID = "save-case";
    private static final String SAVE_AND_CREATE_ANOTHER_BUTTON_XPATH = "//button/span[text()='Save and create another']";
    private static final String CANCEL_BUTTON_XPATH = "//button/span[text()='Cancel']";

    public CreateCasePage openPage() {
        return this;
    }

    @Step("Verify whether create case page opened")
    public CreateCasePage isPageOpened() {
        log.info("Verify whether create case page opened");
        try {
            $(By.id(SAVE_BUTTON_ID)).shouldBe(Condition.visible);
        } catch (Exception e) {
            log.error("Create case page is not opened");
        }
        return this;
    }

    public static void placeholderTextShouldBeVisible() {
//        you can check placeholder Attribute text:
//
//        attribute("placeholder", "some text") // $(“#list li”).shouldHave(attribute(“placeholder”, “some text”))
    }

    public void fillOutTestCaseForm(Case testCase) {
        //Basic
        new Input().write("Title", "title", testCase.getTitle());
        new DropDown().setDropdownValue("Status", testCase.getStatus());
        new TextArea().write("Description", testCase.getDescription());
        new DropDown().setSuiteDropdownValue(testCase.getSuite());
        new DropDown().setDropdownValue("Severity", testCase.getSeverity());
        new DropDown().setDropdownValue("Priority", testCase.getPriority());
        new DropDown().setDropdownValue("Type", testCase.getType());
        new DropDown().setDropdownValue("Layer", testCase.getLayer());
        new DropDown().setDropdownValue("Is flaky", testCase.getIsFlaky());
        new DropDown().setMilestoneDropdownValue(testCase.getMilestone());
        new DropDown().setDropdownValue("Behavior", testCase.getBehavior());
        new DropDown().setDropdownValue("Automation status", testCase.getAutomationStatus());
        new CheckBox().selectCheckBoxOption("To be automated", "ToBeAutomated", testCase.isCheckBoxChecked());
        //Conditions
        new Input().write("Pre-conditions", "0-preconditions", testCase.getPreConditions());
        new Input().write("Post-conditions", "0-postconditions", testCase.getPostConditions());
        //Tags
        new DropDown().setTagsValue("Tags", testCase.getTags());
        //Attachments
        new Input().uploadFile(testCase.getAddAttachment());
        //Parameters
        new Input().addParameters("Parameter title", testCase.getParameterTitle());
        new Input().addParameters("Parameter values", testCase.getParameterValue());
        //Test Case Steps
        new DropDown().setTestCaseStepsDropdownValue(testCase.getTestCaseStepsDropDownOption());

    }

    public void editSomeFieldsFromTestCaseForm() {

    }

    public ProjectDetailsPage clickSaveButton() {
        $(By.id(SAVE_BUTTON_ID)).click();
        return new ProjectDetailsPage();
    }

    public CreateCasePage clickSaveAndCreateAnotherButton() {
        $(By.xpath(SAVE_AND_CREATE_ANOTHER_BUTTON_XPATH)).click();
        return this;
    }

    public ProjectDetailsPage clickCancelButton() {
        $(By.xpath(CANCEL_BUTTON_XPATH)).click();
        return new ProjectDetailsPage();
    }
}

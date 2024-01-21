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

    public static void isPlaceholderPresent() {

    }

    public static void placeholderTextShouldBeVisible() {

    }
//    File file = new File("src/test/resources/58a05716650f315a32542f8e.png");
//
//        driver.findElement(By.xpath("//button[text()='Add attachment']")).sendKeys(file.getAbsolutePath());
//
//        Assert.assertEquals(file.getName(), "58a05716650f315a32542f8e.png");
    public void fillOutTestCaseForm(Case testCase) {

        new Input().write("Title", "title", testCase.getTitle());
        new DropDown().setDropdownValue("Status", testCase.getStatus());
        new TextArea().write("Description", testCase.getDescription());
        new DropDown().setMilestoneDropdownValue(testCase.getMilestone());
        new DropDown().setSuiteDropdownValue(testCase.getSuite());
//        new DropDown().setTestCaseStepsDropdownValue(testCase.getTestCaseSteps());
//        new DropDown();
//        new DropDown();
//        new DropDown();
//        new DropDown();
//        new DropDown();
//        new DropDown();
//        new CheckBox();
//        new Input();
//        new Input();
//
        new Input().uploadFile(testCase.getAddAttachment());
//        new Button();
//        new Input();
//        new Button();
//        new Input();
//        new Button();
//        new Button();
//
//        new Button();
//        new Button();
//        new DropDown();

    }

    public void removeSomeFieldsFromTestCaseForm() {

    }

    public ProjectDetailsPage clickSaveButton() {
        $(By.id(SAVE_BUTTON_ID)).click();
        return new ProjectDetailsPage();
    }

    public void clickSaveAndCreateAnotherButton() {
        $(By.xpath(SAVE_AND_CREATE_ANOTHER_BUTTON_XPATH)).click();
    }

    public void clickCancelButton() {
        $(By.xpath(CANCEL_BUTTON_XPATH)).click();
    }
}

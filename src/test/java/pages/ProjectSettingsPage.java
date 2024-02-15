package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class ProjectSettingsPage extends BasePage {

    private final static String PROJECT_NAME_FIELD_ID = "project-name";
    private final static String PROJECT_UPDATE_SETTINGS_BUTTON_XPATH = "//*[text()=' Update settings']/..";
    private static final String PROJECT_SUCCESSFUL_UPDATING_FLASH_MESSAGE_XPATH = "//script[@id='flashMessages']/following::div[@id='layout']" +
            "//*[text()='%s']";

    @Override
    @Step("Open projects settings page")
    public ProjectSettingsPage openPage(String path) {
        log.info("Open projects settings page");
        open(String.format(BASE_URL + "/project/%s/settings/general", path));
        waitForPageLoaded();
        return this;
    }

    @Override
    @Step("Verify if projects settings page is opened")
    public ProjectSettingsPage isPageOpened() {
        log.info("Verify if projects settings page is opened");
        try {
            $(By.xpath(PROJECT_UPDATE_SETTINGS_BUTTON_XPATH)).shouldBe(Condition.visible);
        } catch (Exception e) {
            log.error("Project list page is not opened");
        }
        return this;
    }

    @Step("Remove project")
    public ProjectSettingsPage removeProjectName(String projectName) {
        log.info("Remove project '{}'", projectName);
        for (int i = 0; i < projectName.length(); i++) {
            $(By.id(PROJECT_NAME_FIELD_ID)).sendKeys(Keys.BACK_SPACE);
        }
        return this;
    }

    @Step("Update project")
    public ProjectSettingsPage updateProjectName(String newProjectName) {
        log.info("Updated project name is '{}'", newProjectName);
        $(By.id(PROJECT_NAME_FIELD_ID)).setValue(newProjectName);
        return this;
    }

    @Step("Click update settings button")
    public ProjectsListPage clickUpdateSettingsButton() {
        log.info("Click update settings button");
        $(By.xpath(PROJECT_UPDATE_SETTINGS_BUTTON_XPATH)).click();
        return new ProjectsListPage();
    }

    @Step("Wait till successful updating message appears")
    public ProjectSettingsPage waitTillSuccessfulUpdatingMessageAppears(String message) {
        log.info("Wait till successful updating message '{}' appears", message);
        $(By.xpath(String.format(PROJECT_SUCCESSFUL_UPDATING_FLASH_MESSAGE_XPATH, message))).shouldBe(Condition.visible);
        return this;
    }

    @Step("Go back to project list page")
    public ProjectsListPage goBackToPreviousPage() {
        log.info("Go back to project list page");
        getWebDriver().navigate().back();
        return new ProjectsListPage();
    }
}


package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class ProjectsListPage extends BasePage {

    private static final String CREATE_NEW_PROJECT_BUTTON_ID = "createButton";

    private static final String PROJECT_NAME_XPATH = "//a[text()='%s']";
    private static final String PROJECT_DETAILS_PAGE_HEADER = "//h1[text()=' repository']";


    @Override
    @Step("Open projects list page")
    public ProjectsListPage openPage() {
        log.info("Open projects list page");
        open(BASE_URL + "/projects");
        waitForPageLoaded();
        return this;
    }

    @Override
    @Step("Verify if projects list page opened")
    public ProjectsListPage isPageOpened() {
        log.info("Verify if projects list page opened");
        try {
            $(By.id(CREATE_NEW_PROJECT_BUTTON_ID)).shouldBe(Condition.visible);
        } catch (Exception e) {
            log.error("Project list page is not opened");
        }
        return this;
    }

    @Step("Click create new project button")
    public ProjectsListPage clickCreateNewProjectButton() {
        log.info("Click create new project button");
        $(By.id(CREATE_NEW_PROJECT_BUTTON_ID)).click();
        return this;
    }

    @Step("Verify whether the project is created")
    public ProjectDetailsPage waitTillProjectCreated() {
        log.info("Verify whether the project is created");
        $(By.xpath((PROJECT_DETAILS_PAGE_HEADER))).shouldBe(Condition.visible);
        return new ProjectDetailsPage();
    }

    @Step("Get project name")
    public String getProjectName() {
        log.info("Get project name");
        return $(By.xpath(PROJECT_NAME_XPATH)).getText();
    }

    @Step("Open project")
    public ProjectDetailsPage openProjectDetails(String projectName) {
        log.info("Open project");
        $(By.xpath(String.format(PROJECT_NAME_XPATH, projectName))).click();
        waitForPageLoaded();
        return new ProjectDetailsPage();
    }
}

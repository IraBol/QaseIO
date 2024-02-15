package pages;

import adapters.ProjectAdapter;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class ProjectsListPage extends BasePage {

    private static final String CREATE_NEW_PROJECT_BUTTON_ID = "createButton";
    private static final String PROJECT_NAME_XPATH = "//a[text()='%s']";
    private static final String PROJECT_DETAILS_PAGE_HEADER_XPATH = "//h1[text()=' repository']";
    private static final String PROJECT_ROWS_PER_PAGE_DROPDOWN_XPATH = "//label[text()='Rows per page:']/following-sibling::div//span";
    private static final String PROJECT_ROWS_PER_PAGE_DROPDOWN_OPTION = "//label[text()='Rows per page:']/following-sibling::div//*[text()='%s']";
    private static final String MEATBALLS_MENU_BUTTON_XPATH = "//a[text()='%s']/../following::button";
    private static final String REMOVE_PROJECT_BUTTON_XPATH = "//a[text()='%s']/../following::button/following::*[text()='Remove']";
    private static final String CONFIRM_REMOVAL_MODAL_WINDOW_BUTTON_XPATH = "//*[text()='Are you sure that you want to delete the project \"%s\"?']" +
            "/../following::*[text()='Delete project']/..";
    private static final String PROJECT_SETTINGS_BUTTON_XPATH = "//a[text()='%s']/../following::button/following::*[text()='Settings']";


    @Override
    @Step("Open projects list page")
    public ProjectsListPage openPage(String path) {
        log.info("Open projects list page");
        open(String.format(BASE_URL + "%s", path));
        waitForPageLoaded();
        return this;
    }

    @Override
    @Step("Verify if projects list page is opened")
    public ProjectsListPage isPageOpened() {
        log.info("Verify if projects list page is opened");
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
        $(By.xpath((PROJECT_DETAILS_PAGE_HEADER_XPATH))).shouldBe(Condition.visible);
        return new ProjectDetailsPage();
    }

    @Step("Get project name")
    public String getProjectName() {
        log.info("Get project name");
        return $(By.xpath(PROJECT_NAME_XPATH)).getText();
    }

    @Step("Open project")
    public ProjectDetailsPage openProjectDetails(String projectName) {
        log.info("Open project '{}'", projectName);
        $(By.xpath(String.format(PROJECT_NAME_XPATH, projectName))).click();
        waitForPageLoaded();
        return new ProjectDetailsPage();
    }

    @Step("Open 'Rows per page' dropdown")
    public ProjectsListPage openRowsPerPageDropdown() {
        log.info("Open 'Rows per page' dropdown");
        $(By.xpath(PROJECT_ROWS_PER_PAGE_DROPDOWN_XPATH)).
                shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        return this;
    }

    @Step("Set 'Rows per page' dropdown option")
    public ProjectsListPage setRowsPerPageDropdownOption(String option) {
        log.info("Set 'Rows per page' dropdown option '{}'", option);
        $(By.xpath(String.format(PROJECT_ROWS_PER_PAGE_DROPDOWN_OPTION, option))).click();
        waitForPageLoaded();
        return this;
    }

    @Step("Set number of projects displayed on the page")
    public void setNumberOfProjectsDisplayed() {
        log.info("Set number of projects displayed on the page");

        ProjectAdapter projectAdapter = new ProjectAdapter();

        int size = projectAdapter.getAllProjects().getTotal();
        System.out.println(size);

        if (size > 0 & size <= 15) {
            openRowsPerPageDropdown();
            setRowsPerPageDropdownOption("15");
        } else if (size > 15 & size <= 20) {
            openRowsPerPageDropdown();
            setRowsPerPageDropdownOption("20");
        } else if (size > 20 & size <= 50) {
            openRowsPerPageDropdown();
            setRowsPerPageDropdownOption("50");
        }
    }

    @Step("Open project meatballs menu")
    public ProjectsListPage openProjectMeatballsMenu(String projectName) {
        log.info("Open '{}' project meatballs menu", projectName);
        $(By.xpath(String.format(MEATBALLS_MENU_BUTTON_XPATH, projectName))).
                shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        return this;
    }

    @Step("Open project settings")
    public ProjectsListPage openProjectSettings(String projectName) {
        log.info("Open '{}' project settings", projectName);
        $(By.xpath(String.format(PROJECT_SETTINGS_BUTTON_XPATH, projectName))).click();
        waitForPageLoaded();
        return this;
    }

    @Step("Remove project")
    public ProjectsListPage removeProject(String projectName) {
        log.info("Remove '{}' project", projectName);
        $(By.xpath(String.format(REMOVE_PROJECT_BUTTON_XPATH, projectName))).click();
        return this;
    }

    @Step("Confirm project removal")
    public ProjectsListPage confirmProjectRemoval(String projectName) {
        log.info("Confirm '{}' project removal", projectName);
        $(By.xpath(String.format(CONFIRM_REMOVAL_MODAL_WINDOW_BUTTON_XPATH, projectName))).click();
        return this;
    }

    @Step("Refresh project list page")
    public ProjectsListPage refreshPage() {
        log.info("Refresh project list page");
        getWebDriver().navigate().refresh();
        return this;
    }
}

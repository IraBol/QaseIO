package pages;

import com.codeborne.selenide.Condition;
import dto.Project;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
@Log4j2
public class CreateProjectPage extends BasePage{

    private static final String PROJECT_NAME_INPUT_ID = "project-name";

    private static final String PROJECT_CODE_INPUT_ID = "project-code";

    private static final String DESCRIPTION_TEXT_AREA_ID = "description-area";

    private static final String CREATE_PROJECT_BUTTON_CSS = "[type=submit]";

    public CreateProjectPage openPage(String path) {
        log.info("Open create project page");
        open(String.format(BASE_URL + "%s", path));
        waitForPageLoaded();
        return this;
    }

    @Override
    public CreateProjectPage isPageOpened() {
        log.info("Verify whether create project page is opened");
        try {
            $(By.cssSelector(CREATE_PROJECT_BUTTON_CSS)).shouldBe(Condition.visible);
        } catch (Exception e) {
           log.error("Create project page is not opened");
        }
        return this;
    }

    @Step("Fill out project form")
    public CreateProjectPage fillOutProjectForm(Project project) {
        log.info("Fill out project form: title, name, description");
//        $(By.id(PROJECT_NAME_INPUT_ID)).setValue(projectName);
//        $(By.id(PROJECT_CODE_INPUT_ID)).setValue(projectCode);
//        $(By.id(DESCRIPTION_TEXT_AREA_ID)).setValue(description);
        return this;
    }

    @Step("Click create project button")
    public ProjectsListPage clickCreateProjectButton() {
        log.info("Click create project button");
        $(By.cssSelector(CREATE_PROJECT_BUTTON_CSS)).click();
        return new ProjectsListPage();
    }

}

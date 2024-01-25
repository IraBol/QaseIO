package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class ProjectDetailsPage extends BasePage {

    private static final String CREATE_SUITE_BUTTON_ID = "create-suite-button";
    private static final String CREATE_CASE_BUTTON_ID = "create-case-button";

    @Override
    @Step("Open project details page")
    public ProjectDetailsPage openPage(String path) {
        log.info("Open project details page");
        open(String.format(BASE_URL + "%s", path));
        waitForPageLoaded();
        return this;
    }

    @Override
    @Step("Verify whether project details page is opened")
    public ProjectDetailsPage isPageOpened() {
        log.info("Verify whether project details page is opened");
        try {
            $(By.id(CREATE_SUITE_BUTTON_ID)).shouldBe(Condition.visible);
            $(By.id(CREATE_CASE_BUTTON_ID)).shouldBe(Condition.visible);
        } catch (Exception e) {
            log.error("Project details page is not opened");
        }
        return this;
    }

    public CreateCasePage clickCreateCaseButton() {
        $(By.id(CREATE_CASE_BUTTON_ID)).click();
        waitForPageLoaded();
        return new CreateCasePage();
    }
}

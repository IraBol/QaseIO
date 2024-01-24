package tests;

import dto.Case;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.io.File;

public class CreateCaseTest extends BaseTest {

    @Test
    public void titleFieldPlaceholderTextShouldBeVisible() {
        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                titleFieldPlaceholderTextShouldBeVisible();
    }

    @Test
    public void caseCreatedSuccessfulMessageShouldBeVisible() {

        Case caseTest = Case.builder().
                title("Modal").
                build();

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                fillOutTestCaseForm(caseTest);
        createCasePage.
                clickSaveButton();
        createCasePage.
                waitTillSuccessfulCaseCreationMessageAppears();
    }

    @Test
    public void createTestCase() {

        String caseTitle = faker.funnyName().name();
        String caseDescription = faker.lorem().paragraph(3);
        String caseConditions = faker.lorem().paragraph(1);

        Case caseTest = Case.builder().

                title(caseTitle).
                status("Draft").
                description(caseDescription).
                suite("Lol suite").
                severity("Minor").
                priority("Low").
                type("Smoke").
                layer("E2E").
                isFlaky("Yes").
                milestone("Release 1.0").
                behavior("Positive").
                automationStatus(null).
                isCheckBoxChecked(false).

                preConditions(caseConditions).
                postConditions(caseConditions).
                //"Lol tag", "Example tag"
                // tags(new String[]{"Lol tag", "Example tag"}).

                        addAttachment(new File("src/test/resources/Screenshot225626.jpg")).

                parameterTitle("test parameter").
                parameterValue("test value").

                testCaseStepsDropdownOption("Gherkin").

                gherkinStepsNumber("1").
                gherkinStepsDropdownOption("Given").
                gherkinStepsNumber("1").
                gherkinStepsInput("I need to prepare some scenario to test").

                gherkinStepsNumber("2").
                gherkinStepsDropdownOption("When").
                gherkinStepsNumber("2").
                gherkinStepsInput("I trigger some actions").

                gherkinStepsNumber("3").
                gherkinStepsDropdownOption("Then").
                gherkinStepsNumber("3").
                gherkinStepsInput("I can see the expected outcome").

        build();


        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                fillOutTestCaseForm(caseTest);
        createCasePage.
                clickSaveButton();


    }

    @Test
    public void emptyRequiredFieldTitleErrorMessageShouldAppear() {

        String caseDescription = faker.lorem().paragraph(3);

        Case caseTest = Case.builder().
                title(null).
                description(caseDescription).
                build();

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                fillOutTestCaseForm(caseTest);
        createCasePage.
                clickSaveButton();
        createCasePage.
                fieldValidationMessageShouldBeVisible();
    }

    @Test
    public void nothingIsFilledOutClickCancelProjectDetailsPageShouldAppear() {

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                clickCancelButton();
        projectDetailsPage.
                isPageOpened();

    }

    @Test
    public void titleIsFilledOutClickCancelModalWindowShouldAppear() {

        Case caseTest = Case.builder().
                title("Modal").
                build();

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                fillOutTestCaseForm(caseTest);
        createCasePage.
                clickCancelButton().
                waitTillModalCloseFormWindowAppears();
    }

    @Test
    public void clickCloseFormCancelButtonCasePageShouldAppear() {

        Case caseTest = Case.builder().
                title("Modal").
                build();

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                fillOutTestCaseForm(caseTest);
        createCasePage.
                clickCancelButton().
                waitTillModalCloseFormWindowAppears().
                clickCloseFormCancelButton().
                isPageOpened();
    }

    @Test
    public void clickCloseFormCrossCancelButtonCasePageShouldAppear() {

        Case caseTest = Case.builder().
                title("Modal").
                build();

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                fillOutTestCaseForm(caseTest);
        createCasePage.
                clickCancelButton().
                waitTillModalCloseFormWindowAppears().
                clickCloseFormCrossCancelButton().
                isPageOpened();
    }

    @Test
    public void clickCloseFormButtonProjectDetailsPageShouldAppear() {

        Case caseTest = Case.builder().
                title("Modal").
                build();

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                fillOutTestCaseForm(caseTest);
        createCasePage.
                clickCancelButton().
                waitTillModalCloseFormWindowAppears().
                clickCloseFormButton();
        projectDetailsPage.
                isPageOpened();
    }
}

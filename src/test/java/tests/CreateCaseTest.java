package tests;

import dto.Case;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.io.File;

public class CreateCaseTest extends BaseTest {

    @Test(description = "Placeholder message for title field should be visible if no text input")
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

    @Test(description = "Success message should appear after successful test creation")
    public void caseCreatedSuccessfulMessageShouldBeVisible() {

        String caseTitle = faker.funnyName().name();

        Case caseCreationTestData = Case.builder().
                title(caseTitle).
                build();

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                fillOutTestCaseForm(caseCreationTestData);
        createCasePage.
                clickSaveButton();
        createCasePage.
                waitTillSuccessfulCaseCreationMessageAppears();
    }

    @Test(description = "Successfully created test case should be saved and appear on project details page")
    public void testCaseShouldBeCreatedSuccessfully() {

        Case caseCreationTestData = Case.builder().

                title("Diploma Test Case Title").
                status("Draft").
                description("This is test case description test. We test large amount of sentences. " +
                        "We are to be sure that there are no bugs in this field.").
                suite("Diploma suite").
                severity("Minor").
                priority("Low").
                type("Smoke").
                layer("E2E").
                isFlaky("Yes").
                milestone("Release 1.0").
                behavior("Positive").
                automationStatus(null).
                isCheckBoxChecked(false).

                preConditions("This is test case pre-condition test").
                postConditions("This is test case post-condition test").
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

//                gherkinStepsNumber("2").
//                gherkinStepsDropdownOption("When").
//                gherkinStepsNumber("2").
//                gherkinStepsInput("I trigger some actions").

//                gherkinStepsNumber("3").
//                gherkinStepsDropdownOption("Then").
//                gherkinStepsNumber("3").
//                gherkinStepsInput("I can see the expected outcome").

        build();

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                fillOutTestCaseForm(caseCreationTestData);
        createCasePage.
                clickSaveButton();
        createCasePage.waitTillCaseCreated("Diploma Test Case Title");
    }

        //Этот тест падает. Возможно, тут настоящий баг
        @Test(description = "After clicking 'Save and create another' button -" +
                " one more test case should be created")
        public void clickSaveAndCreateAnotherOneMoreTestCaseShouldBeCreated() {

            Case firstCreatedTestData = Case.builder().
                    title("First Test Case Created").
                    build();

            Case secondCreatedTestData = Case.builder().
                    title("Second Test Case Created").
                    build();

            createCasePage.
                    openPage("/case/LOL/create").
                    isPageOpened().
                    fillOutTestCaseForm(firstCreatedTestData);
            createCasePage.
                    clickSaveAndCreateAnotherButton().
                    isPageOpened().
                    fillOutTestCaseForm(secondCreatedTestData);
            createCasePage.
                    clickSaveButton();
            projectDetailsPage.
                    isPageOpened();
            createCasePage.waitTillCaseCreated("First Test Case Created");
            createCasePage. waitTillCaseCreated("Second Test Case Created");
        }


    @Test(description = "Required field 'Title' not filled out - " +
            "error message should appear after clicking 'Save' button")
    public void emptyTitleClickSaveErrorMessageShouldAppear() {

        String caseDescription = faker.lorem().paragraph(3);

        Case caseCreationTestData = Case.builder().
                title(null).
                description(caseDescription).
                build();

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                fillOutTestCaseForm(caseCreationTestData);
        createCasePage.
                clickSaveButton();
        createCasePage.
                requiredFieldValidationMessageShouldBeVisible();
    }

    @Test(description = "Required field 'Title' not filled out - " +
            "error message should appear after clicking 'Save and create another' button")
    public void emptyTitleClickSaveAndCreateAnotherErrorMessageShouldAppear() {

        String caseDescription = faker.lorem().paragraph(3);

        Case caseCreationTestData = Case.builder().
                title(null).
                description(caseDescription).
                build();

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                fillOutTestCaseForm(caseCreationTestData);
        createCasePage.
                clickSaveAndCreateAnotherButton();
        createCasePage.
                requiredFieldValidationMessageShouldBeVisible();
    }

    @Test(description = "Fields not filled out - " +
            "project details page should appear w/o test case after clicking 'Cancel' button")
    public void nothingIsFilledOutClickCancelProjectDetailsPageShouldAppear() {

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                clickCancelButton();
        projectDetailsPage.
                isPageOpened();

    }

    @Test(description = "Only 'Title' field is filled out - " +
            "'Close Form' modal window should appear after clicking 'Cancel' button")
    public void titleIsFilledOutClickCancelModalWindowShouldAppear() {

        Case caseCreationTestData = Case.builder().
                title("Modal Window Test").
                build();

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                fillOutTestCaseForm(caseCreationTestData);
        createCasePage.
                clickCancelButton().
                waitTillCloseFormModalWindowAppears();
    }

    @Test(description = "'Create test case' page should appear after clicking modal window 'Cancel' button")
    public void clickCancelModalWindowButtonCasePageShouldAppear() {

        Case caseCreationTestData = Case.builder().
                title("Modal Window Test").
                build();

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                fillOutTestCaseForm(caseCreationTestData);
        createCasePage.
                clickCancelButton().
                waitTillCloseFormModalWindowAppears().
                clickCancelModalWindowButton().
                isPageOpened();
    }

    @Test(description = "'Create test case' page should appear after clicking modal window 'Cross' button")
    public void clickCrossModalWindowButtonCasePageShouldAppear() {

        Case caseCreationTestData = Case.builder().
                title("Modal Window Test").
                build();

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                fillOutTestCaseForm(caseCreationTestData);
        createCasePage.
                clickCancelButton().
                waitTillCloseFormModalWindowAppears().
                clickCrossModalWindowButton().
                isPageOpened();
    }

    @Test(description = "'Project details' page should appear after clicking modal window 'Close form' button")
    public void clickCloseFormModalWindowButtonProjectDetailsPageShouldAppear() {

        Case caseCreationTestData = Case.builder().
                title("Modal Window Test").
                build();

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                fillOutTestCaseForm(caseCreationTestData);
        createCasePage.
                clickCancelButton().
                waitTillCloseFormModalWindowAppears().
                clickCloseFormModalWindowButton();
        projectDetailsPage.
                isPageOpened();
    }
}

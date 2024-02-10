package tests;

import dto.Case;
import dto.factory.CaseFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseUITest;

public class CreateCaseUITest extends BaseUITest {

    @BeforeMethod(description = "Login w/ the correct credentials")
    public void successfulLogin() {

        loginPage.
                openPage("/login").
                isPageOpened().
                fillOutLoginForm(USERNAME, PASSWORD).
                clickSignInButton();

        projectsListPage.
                isPageOpened();
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
                waitTillSuccessfulCaseCreationMessageAppears("Test case was created successfully!");
    }

    @Test(description = "Successfully created test case should be saved and appear on project details page")
    public void testCaseShouldBeCreatedSuccessfully() {

        Case testCase = CaseFactory.getRandom();

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                fillOutTestCaseForm(testCase);
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
                requiredFieldValidationMessageShouldBeVisible("Please fill out this field.");
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
                requiredFieldValidationMessageShouldBeVisible("Please fill out this field.");
    }

    //Почему-то возвращает project list page вместо project details, если переходить по прямой ссылке к созданию TC
    @Test(description = "Fields not filled out - " +
            "project details page should appear w/o test case after clicking 'Cancel' button")
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

    @Test(description = "Only 'Title' field is filled out - " +
            "'Close Form' modal window should appear after clicking 'Cancel' button")
    public void titleIsFilledOutClickCancelModalWindowShouldAppear() {

        Case caseCreationTestData = Case.builder().
                title("Modal Window Test 1").
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
                title("Modal Window Test 2").
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
                title("Modal Window Test 3").
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

    //Почему-то возвращает project list page вместо project details, если переходить по прямой ссылке к созданию TC
    @Test(description = "'Project details' page should appear after clicking modal window 'Close form' button")
    public void clickCloseFormModalWindowButtonProjectDetailsPageShouldAppear() {

        Case caseCreationTestData = Case.builder().
                title("Modal Window Test 4").
                build();

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
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

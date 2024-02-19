package tests;

import dto.Case;
import dto.Project;
import dto.Suite;
import dto.factory.CaseFactory;
import dto.factory.ProjectFactory;
import dto.factory.SuiteFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CreateCaseTest extends BaseTest {

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
    public void createdCaseSuccessfulMessageShouldBeVisible() {

        Project project = ProjectFactory.getRandom();
        Suite suite = SuiteFactory.getRandom();
        Case testCase = CaseFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        suiteAdapter.
                createNewSuite(suite, project.getCode());

        createCasePage.
                openPage("/case/" + project.getCode() + "/create").
                isPageOpened().
                fillOutTestCaseForm(testCase);
        createCasePage.
                clickSaveButton();
        createCasePage.
                waitTillSuccessfulCaseCreationMessageAppears("Test case was created successfully!");
    }

    @Test(description = "Successfully created test case should be saved and appear on project details page")
    public void testCaseShouldBeCreatedSuccessfully() {

        Project project = ProjectFactory.getRandom();
        Suite suite = SuiteFactory.getRandom();
        Case testCase = CaseFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        suiteAdapter.
                createNewSuite(suite, project.getCode());

        createCasePage.
                openPage("/case/" + project.getCode() + "/create").
                isPageOpened().
                fillOutTestCaseForm(testCase);
        createCasePage.
                clickSaveButton();
        createCasePage.waitTillCaseCreated(testCase.getTitle());
    }

    //Этот тест падает, потому что при создании нового через кнопку save & create another просит
    //выбрать Test Case Steps dropdown option, хотя при создании 1го кейса там по дефолту уже стоит Classic

//    @Test(description = "After clicking 'Save and create another' button " +
//            " 2 test cases should be created")
//    public void clickSaveAndCreateAnotherTwoTestCasesShouldBeCreated() {
//
//        Case firstCreatedTestData = Case.builder().
//                title("First Test Case Created").
//                build();
//
//        Case secondCreatedTestData = Case.builder().
//                title("Second Test Case Created").
//                build();
//
//        Project project = ProjectFactory.getRandom();
//
//        projectAdapter.
//                createNewProject(project);
//
//        createCasePage.
//                openPage("/case/" + project.getCode() + "/create").
//                isPageOpened().
//                fillOutTestCaseForm(firstCreatedTestData);
//        createCasePage.
//                clickSaveAndCreateAnotherButton().
//                isPageOpened().
//                fillOutTestCaseForm(secondCreatedTestData);
//        createCasePage.
//                clickSaveButton();
//        projectDetailsPage.
//                isPageOpened();
//        createCasePage.waitTillCaseCreated(firstCreatedTestData.getTitle());
//        createCasePage.waitTillCaseCreated(secondCreatedTestData.getTitle());
//    }


    @Test(description = "Required field 'Title' not filled out - " +
            "error message should appear after clicking 'Save' button")
    public void emptyTitleClickSaveErrorMessageShouldAppear() {

        Case caseCreationTestData = Case.builder().
                title(null).
                description(faker.lorem().paragraph(3)).
                build();

        Project project = ProjectFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        createCasePage.
                openPage("/case/" + project.getCode() + "/create").
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

        Case caseCreationTestData = Case.builder().
                title(null).
                description(faker.lorem().paragraph(3)).
                build();

        Project project = ProjectFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        createCasePage.
                openPage("/case/" + project.getCode() + "/create").
                isPageOpened().
                fillOutTestCaseForm(caseCreationTestData);
        createCasePage.
                clickSaveAndCreateAnotherButton();
        createCasePage.
                requiredFieldValidationMessageShouldBeVisible("Please fill out this field.");
    }

    //сделать позже метод по выбору отображения кол-ва проектов на странице = 50
    @Test(description = "Form not filled out - " +
            "project details page should appear w/o test case after clicking 'Cancel' button")
    public void formNotFilledOutClickCancelProjectDetailsPageShouldAppear() {

        Project project = ProjectFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        projectsListPage.
                refreshPage().
                setNumberOfProjectsDisplayed();
        projectsListPage.
                openProjectDetails(project.getTitle());

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

        Project project = ProjectFactory.getRandom();
        Suite suite = SuiteFactory.getRandom();
        Case testCase = CaseFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        suiteAdapter.
                createNewSuite(suite, project.getCode());

        createCasePage.
                openPage("/case/" + project.getCode() + "/create").
                isPageOpened().
                fillOutTestCaseForm(testCase);
        createCasePage.
                clickCancelButton().
                waitTillCloseFormModalWindowAppears();
    }

    @Test(description = "'Create test case' page should appear after clicking modal window 'Cancel' button")
    public void clickCancelModalWindowButtonCasePageShouldAppear() {

        Project project = ProjectFactory.getRandom();
        Suite suite = SuiteFactory.getRandom();
        Case testCase = CaseFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        suiteAdapter.
                createNewSuite(suite, project.getCode());

        createCasePage.
                openPage("/case/" + project.getCode() + "/create").
                isPageOpened().
                fillOutTestCaseForm(testCase);
        createCasePage.
                clickCancelButton().
                waitTillCloseFormModalWindowAppears().
                clickCancelModalWindowButton().
                isPageOpened();
    }

    @Test(description = "'Create test case' page should appear after clicking modal window 'Cross' button")
    public void clickCrossModalWindowButtonCasePageShouldAppear() {

        Project project = ProjectFactory.getRandom();
        Suite suite = SuiteFactory.getRandom();
        Case testCase = CaseFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        suiteAdapter.
                createNewSuite(suite, project.getCode());

        createCasePage.
                openPage("/case/" + project.getCode() + "/create").
                isPageOpened().
                fillOutTestCaseForm(testCase);
        createCasePage.
                clickCancelButton().
                waitTillCloseFormModalWindowAppears().
                clickCrossModalWindowButton().
                isPageOpened();
    }

    @Test(description = "'Project details' page should appear after clicking modal window 'Close form' button")
    public void clickCloseFormModalWindowButtonProjectDetailsPageShouldAppear() {

        Project project = ProjectFactory.getRandom();
        Suite suite = SuiteFactory.getRandom();
        Case testCase = CaseFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        suiteAdapter.
                createNewSuite(suite, project.getCode());

        projectsListPage.
                refreshPage().
                setNumberOfProjectsDisplayed();
        projectsListPage.
                openProjectDetails(project.getTitle());

        projectDetailsPage.
                isPageOpened().
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                fillOutTestCaseForm(testCase);
        createCasePage.
                clickCancelButton().
                waitTillCloseFormModalWindowAppears().
                clickCloseFormModalWindowButton();
        projectDetailsPage.
                isPageOpened();
    }
}

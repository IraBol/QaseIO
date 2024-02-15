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

public class CaseDetailsTest extends BaseTest {

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

    @Test(description = "Validate created test case details")
    public void validateCreatedTestCaseDetails() {

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
                waitTillCaseCreated(testCase.getTitle());

        caseDetailsPage.
                openPage(project.getCode());

        caseDetailsPage.
                validateGeneralTabInfo(testCase).
                validatePropertiesTabInfo(testCase);
    }
}

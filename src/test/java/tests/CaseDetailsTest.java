package tests;

import dto.Case;
import dto.factory.CaseFactory;
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

        Case testCase = CaseFactory.getRandom();

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened();

        createCasePage.
                openPage("/case/LOL/create").
                isPageOpened().
                fillOutTestCaseForm(testCase);
        createCasePage.
                clickSaveButton();
        createCasePage.
                waitTillCaseCreated(testCase.getTitle());

        caseDetailsPage.
                openTestCase(testCase.getTitle()).
                validateGeneralTabInfo(testCase).
                openPropertiesTab().
                validatePropertiesTabInfo(testCase);
    }
}

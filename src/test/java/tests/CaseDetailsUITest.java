package tests;

import dto.Case;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseUITest;

public class CaseDetailsUITest extends BaseUITest {

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

        //Данные можно на весь класс использовать, а можно на конкретный метод
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
                isPropertiesTabCheckBoxChecked(true).

                preConditions("This is test case pre-condition test").
                postConditions("This is test case post-condition test").

                attachmentTitle("Screenshot225626.jpg").

                parameterTitle("test parameter").
                parameterValue("test value").

                testCaseStepsDropdownOption("Gherkin").

                gherkinStepsNumber("1").
                gherkinStepsDropdownOption("Given").
                gherkinStepsNumber("1").
                gherkinStepsInput("I need to prepare some scenario to test").

        build();

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                isPageOpened();

        caseDetailsPage.
                openTestCase("Diploma Test Case Title").
                validateGeneralTabInfo(caseCreationTestData).
                openPropertiesTab().
                validatePropertiesTabInfo(caseCreationTestData);
    }
}

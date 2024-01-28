package tests;

import dto.Case;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CaseDetailsTest extends BaseTest {

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
                isCheckBoxChecked(true).

                preConditions("This is test case pre-condition test").
                postConditions("This is test case post-condition test").

                //"Lol tag", "Example tag"
                // tags(new String[]{"Lol tag", "Example tag"}).

                        attachmentTitle("Screenshot225626.jpg").

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

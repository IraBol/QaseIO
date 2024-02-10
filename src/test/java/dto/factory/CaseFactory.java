package dto.factory;

import com.github.javafaker.Faker;
import dto.Case;

import java.io.File;

public class CaseFactory {

    public static Case getRandom() {

        Faker faker = new Faker();

        Case caseCreationTestData = Case.builder().

                title(faker.book().title()).
                status("Draft").
                description(faker.lorem().paragraph(1)).
                suite("Diploma suite").
                severity("Minor").
                priority("Low").
                type("Smoke").
                layer("E2E").
                isFlaky("Yes").
                milestone(null).
                behavior("Positive").
                automationStatus(null).
                isCheckBoxChecked(false).
                //this field is for Case Details page
                isPropertiesTabCheckBoxChecked(true).

                preConditions("This is test case pre-condition test").
                postConditions("This is test case post-condition test").

                addAttachment(new File("src/test/resources/Screenshot225626.jpg")).
                //this field is for Case Details page
                attachmentTitle("Screenshot225626.jpg").

                parameterTitle("test parameter").
                parameterValue("test value").

                testCaseStepsDropdownOption("Gherkin").

                gherkinStepsNumber("1").
                gherkinStepsDropdownOption("Given").
                gherkinStepsNumber("1").
                gherkinStepsInput("I need to prepare some scenario to test").

                build();

        return caseCreationTestData;
    }
}

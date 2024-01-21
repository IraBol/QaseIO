package tests;

import dto.Case;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.io.File;

public class CreateCaseTest extends BaseTest {

    @Test
    public void createTestCase() {

        String caseTitle = faker.funnyName().name();
        String caseDescription = faker.lorem().paragraph(3);

        Case caseTest = Case.builder().
                title(caseTitle).
                description(caseDescription).
                status("Draft").
                milestone("Release 1.0").
                suite("Lol suite").
                addAttachment(new File("src/test/resources/Screenshot225626.jpg")).
                build();

        projectsListPage.
                openProjectDetails("LOL");

        projectDetailsPage.
                clickCreateCaseButton();

        createCasePage.
                isPageOpened().
                fillOutTestCaseForm(caseTest);
        createCasePage.
                clickSaveButton();


    }
}

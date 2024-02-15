package tests;

import dto.Project;
import dto.factory.ProjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ProjectsListTest extends BaseTest {

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

    @Test
    public void removeProject() {

        Project project = ProjectFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        projectsListPage.
                refreshPage().
                setNumberOfProjectsDisplayed();
        projectsListPage.
                openProjectMeatballsMenu(project.getTitle()).
                removeProject(project.getTitle()).
                confirmProjectRemoval(project.getTitle());
        projectDetailsPage.
                openPage(project.getCode()).
                waitTillProjectNotFoundErrorAppears("404");

    }

    @Test
    public void projectSuccessfulUpdatingMessageShouldBeVisible() {

        Project project = ProjectFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        projectsListPage.
                refreshPage().
                setNumberOfProjectsDisplayed();
        projectsListPage.
                openProjectMeatballsMenu(project.getTitle()).
                openProjectSettings(project.getTitle());

        projectSettingsPage.
                isPageOpened().
                removeProjectName(project.getTitle()).
                updateProjectName("UPDATEDNAME").
                clickUpdateSettingsButton();
        projectSettingsPage.
                waitTillSuccessfulUpdatingMessageAppears("Project settings were successfully updated!");
    }

    @Test
    public void projectNameShouldBeUpdatedSuccessfully() {

        Project project = ProjectFactory.getRandom();

        projectAdapter.
                createNewProject(project);

        projectsListPage.
                refreshPage();

        projectSettingsPage.
                openPage(project.getCode()).
                isPageOpened().
                removeProjectName(project.getTitle()).
                updateProjectName("UPDATEDNAME").
                clickUpdateSettingsButton();
        projectSettingsPage.
                goBackToPreviousPage();

        projectsListPage.
                setNumberOfProjectsDisplayed();
        projectsListPage.
                openProjectDetails("UPDATEDNAME");
    }
}


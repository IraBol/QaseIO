package tests;

import dto.Project;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;

public class ProjectsListTest extends BaseTest {

    //залогиниться, открыть стр список проектов, проверить список
    //посчитать проекты: если > 10, то
    //label[text()='Rows per page:']/following-sibling::div//span
    ////label[text()='Rows per page:']/following-sibling::div//span/../*[text()='20']
    @Test
    public void setNumberOfProjectsDisplayed() {
        loginPage.
                openPage("/login").
                isPageOpened().
                fillOutLoginForm(USERNAME, PASSWORD).
                clickSignInButton();

        projectsListPage.
                isPageOpened();
//это отдельный метод, sout для дебага
        int size = projectAdapter.getAllProjects().getTotal();
        System.out.println(size);
//это отдельный метод + внутри разобраться с chain of invoc
        if (size > 0 & size <= 15) {
            projectsListPage.openRowsPerPageDropdown();
            projectsListPage.setRowsPerPageDropdownOption("15");
        } else if (size > 15 & size <= 20) {
            projectsListPage.openRowsPerPageDropdown();
            projectsListPage.setRowsPerPageDropdownOption("20");
        } else if (size > 20 & size <= 50) {
            projectsListPage.openRowsPerPageDropdown();
            projectsListPage.setRowsPerPageDropdownOption("50");
        }
//это удалить потом, это проверка метода
        projectsListPage.openProjectDetails("IRAFORSEARCH");

    }

    @Test
    public void test() {
        loginPage.
                openPage("/login").
                isPageOpened().
                fillOutLoginForm(USERNAME, PASSWORD).
                clickSignInButton();

        projectsListPage.
                isPageOpened();
//        projectsListPage.setNumberOfProjectsDisplayed();
    }
//            for (Project project : projects) {
//                projectAdapter.deleteProjectByCode(project.getCode());
//            }
//        }

    //Здесь проект будет создаваться через апи
    //Далее, на юай можно обновить проект и валидировать данные, например изменить название, удаляем через бэкспейс

    //можно через апи удалить проект - проверить на юай по прямой ссылке?

    //и т.д.


//    @Test
//    public void projectShouldBeCreated() {
//        loginPage.openPage();
//        loginPage.login();
//        projectsListPage.openPage();
//        projectsListPage.createProject();
//        projectsListPage.waitTillCreated();
////        String projectName = faker.name().firstName() + faker.name().lastName();
//        String projectName = faker.funnyName().name();
//
//
//
//    }

//    @Test(description = "Create new project")
//    public void projectShouldBeCreated() {
//        projectsListPage.clickCreateNewProjectButton().
//                fillInProjectName("TestProject").
//                fillInProjectCode("TP").
//                fillInDescription("TestProject").
//                clickCreateProjectButton();
//        assertTrue(projectPage.isPageOpened(), "Project is not created");
//        assertEquals(projectPage.getProjectName(), "TP repository", "Project name is not correct");
//    }
}


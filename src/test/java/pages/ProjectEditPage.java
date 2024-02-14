package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class ProjectEditPage {

    //a[text()='Bill Loney']/../following::button - ... button
    //a[text()='Bill Loney']/../following::button/following::*[text()='Settings'] - remove/Settings
    //*[text()=' Update settings']/.. - update Settings button
    //[id=project-name] - project name field

    //Project settings were successfully updated!

    ////script[@id='flashMessages']/following::div[@id='layout']//*[text()='Project settings were successfully updated!']
    //вернуться
    //найти новое имя на странице
    //*[text()='Are you sure that you want to delete the project "Bill Lon"?']/../following::*[text()='Delete project']/.. - delete project button

    //click relevant project ... button
    //click remove button
    //click delete project modal window button
    //waitTillRemoved - здесь ищем по прямой ссылке или по имени (тогда should not be visible)
    //click settings button
    //changeProjectName

    public void removeProjectName(String projectName) {
        for (int i = 0; i < projectName.length(); i++) {
            $(By.xpath("insert xpath here")).sendKeys(Keys.BACK_SPACE);
        }
    }

    public void updateProjectName() {
            $(By.xpath("insert xpath here")).setValue("New project name here");
    }


}

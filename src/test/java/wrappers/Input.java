/*
Upload file
Работает с элементами типа input. Искать в дереве DOM нужно что-то типа <input type='file'...>
Может быть даже в конце html файла. Нужно искать по всему доку/дому
 */
package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Input {

    private static final String TC_INPUT_XPATH = "//label[text()='%s']/following-sibling::*/input[@id='%s']";
    private static final String TC_ADD_ATTACHMENT_BUTTON_XPATH = "//button[text()='Add attachment']";
    private static final String TC_INPUT_FILE_XPATH = "//div[@data-react-modal-body-trap][2]/following-sibling::input[@type='file']";
    private static final String TC_ADD_PARAMETER_BUTTON_XPATH = "//button/span[text()='Add parameter']";
    private static final String TC_PARAMETER_INPUT_XPATH = "//label[text()='%s']/following::input";
    private static final String TC_GHERKIN_ADD_STEP_BUTTON_ID = "gherkin-add-step-btn";

    //label[text()='Parameter title']/following::input

    public void write(String label, String inputId, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(String.format(TC_INPUT_XPATH, label, inputId))).sendKeys(text);
        }
    }

    public void uploadFile(File file) {
        log.info("Add attachment");
        if (file != null) {
            $(By.xpath(TC_ADD_ATTACHMENT_BUTTON_XPATH)).click();
            $(By.xpath(TC_INPUT_FILE_XPATH)).uploadFile(file);
        }
    }

    public void addParameters(String label, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(TC_ADD_PARAMETER_BUTTON_XPATH)).click();
            $(By.xpath(String.format(TC_PARAMETER_INPUT_XPATH, label))).sendKeys(text);
        }
    }

    public void addGherkinSteps() {
        log.info("Add test case steps");
        if () {
            $(By.id(TC_GHERKIN_ADD_STEP_BUTTON_ID)).click();
            //div[text()='Test Case Steps']/following::tr//div[text()='Given']
            //div[text()='Test Case Steps']/following::tr//div[text()='1']/following::div[text()='Given']
        }
    }


    //label[text()='Pre-conditions']/following-sibling::*/input[@id='0-preconditions']
    //label[text()='Post-conditions']/following-sibling::*/input[@id='0-postconditions']

    //id="gherkin-add-step-btn" - add step
    //div[text()='Given']/following-sibling::input[@value='Given'][1]
}

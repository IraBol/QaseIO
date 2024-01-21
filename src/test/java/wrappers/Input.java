package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Input {

    private static final String INPUT_XPATH = "//label[text()='%s']/following-sibling::*/input[@id='%s']";
    private static final String ADD_ATTACHMENT_BUTTON_XPATH = "//button[text()='Add attachment']";

    //Upload file работает с элементами типа input. Искать в дереве DOM нужно что-то типа <input type='file'...>
    //Может быть даже в конце html файла. Нужно искать по всему доку/дому
    private static final String INPUT_FILE_XPATH = "//div[@data-react-modal-body-trap][2]/following-sibling::input[@type='file']";
    File file = new File("src/test/resources/Screenshot225626.jpg");

    public void write(String label, String inputId, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(String.format(INPUT_XPATH, label, inputId))).sendKeys(text);
        }
    }

    public void uploadFile(File file) {
        log.info("Add attachment");
        if (file != null) {
            $(By.xpath(ADD_ATTACHMENT_BUTTON_XPATH)).click();
            $(By.xpath(INPUT_FILE_XPATH)).uploadFile(file);
        }
    }

    public void addCondition() {
        log.info("Add conditions");
        if (file != null) {

        }
    }

    public void addParameters() {
        log.info("Add parameters");
        if (file != null) {

        }
    }

    public void addSteps() {
        log.info("Add test case steps");
        if (file != null) {

        }
    }


    //label[text()='Pre-conditions']/following-sibling::*/input[@id='0-preconditions']
    //label[text()='Post-conditions']/following-sibling::*/input[@id='0-postconditions']

    //id="gherkin-add-step-btn" - add step
    ////div[text()='Given']/following-sibling::input[@value='Given'][1]
}

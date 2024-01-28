/*
Upload file
Работает с элементами типа input. Искать в дереве DOM нужно что-то типа <input type='file'...>
Может быть даже в конце html файла. Нужно искать по всему доку/дому

Verify uploaded file(preferably images)
Проверять загруженный файл, в частности картинку, можно через API/UI. Если проверяем через UI, то варианты:

1) проверка через JS executor (нюанс - нужно знать js, что правильно писать скрипт). Варианты:
Boolean isImageLoaded = (Boolean) ((JavascriptExecutor)driver).
            executeScript("return arguments[0].complete && typeof arguments[0].
            naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageWebElement);
Это нужно добавить в метод и сравнивать через if возвращаемое значение с true/false - в зависимости от результата сравнения
           возвращать true/false вместо sout. (нюанс - нужен driver, а в selenide не объявляется driver)

или

Object result = ((JavascriptExecutor) driver).executeScript(
   "return arguments[0].complete && "+
   "typeof arguments[0].naturalWidth != \"undefined\" && "+
   "arguments[0].naturalWidth > 0", image);

    boolean loaded = false;
    if (result instanceof Boolean) {
      loaded = (Boolean) result;
      System.out.println(loaded);
    }

Вариант похож на тот, что выше, более докрученный и его тоже нужно в метод добавить. Смысл такой же.

2) Selenium getSize() method
Можно найти элемент(image) и взять его высоту/ширину(.getSize().height & .getSize.heightWidth) - если они
подходят по заданным параметрам, то картинка есть, если размер отличается (больше/меньше), то false.

Например:

WebElement searchBox = driver.findElement(By.name("q"));
        Dimension dim = searchBox.getSize();

        System.out.println("Width : " + dim.width);
        System.out.println("Height : " + dim.height);

Только нужно добавить это в boolean метод и через if сравнивать значения с ожидаемыми и уже возвращать в результате true/false
 */
package wrappers;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Input {

    //_________________________________________CREATE TEST CASE PAGE__________________________________________________\\
    private static final String TC_INPUT_XPATH = "//label[text()='%s']/following-sibling::*/input[@id='%s']";
    private static final String TC_ADD_ATTACHMENT_BUTTON_XPATH = "//button[text()='Add attachment']";
    private static final String TC_INPUT_FILE_XPATH = "//div[@data-react-modal-body-trap][2]/following-sibling::input[@type='file']";
    private static final String TC_ADD_PARAMETER_BUTTON_XPATH = "//button/span[text()='Add parameter']";
    private static final String TC_PARAMETER_INPUT_XPATH = "//label[text()='%s']/following::input";
    private static final String TC_GHERKIN_INPUT_XPATH = "//div[text()='%s']/following::tr//div[text()='%s']/following::input[3]";


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

    public void writeParameterTitle(String label, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(TC_ADD_PARAMETER_BUTTON_XPATH)).click();
            $(By.xpath(String.format(TC_PARAMETER_INPUT_XPATH, label))).sendKeys(text);
        }
    }

    public void writeParameterValue(String label, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(String.format(TC_PARAMETER_INPUT_XPATH, label))).sendKeys(text);
        }
    }

    public void writeGherkinSteps(String label, String inputNumber, String text) {
        log.info("Write '{}' into gherkin input field", text);
        if (text != null) {
            $(By.xpath(String.format(TC_GHERKIN_INPUT_XPATH, label, inputNumber))).sendKeys(text);
        }
    }

    //_________________________________________TEST CASE DETAILS PAGE__________________________________________________\\

    private static final String TC_DETAILS_TITLE_XPATH = "//div[text()='%s']";
    private static final String TC_DETAILS_LOADED_ATTACHMENT_XPATH = "//h3[text()='%s']/..//*[text()='%s']/ancestor::a";
    private static final String TC_DETAILS_GENERAL_XPATH = "//%s[text()='%s']/..//*[text()='%s']";

    public void validateTestCaseDetailsTitle(String text) {
        log.info("Test case title is'{}'", text);
        if (text != null) {
            $(By.xpath(String.format(TC_DETAILS_TITLE_XPATH, text))).shouldBe(Condition.visible);
        }
    }

    public void validateGeneralTabFields(String tag, String value, String text) {
        log.info("'{}' field contains '{}' text", value, text);
        if (text != null) {
            $(By.xpath(String.format(TC_DETAILS_GENERAL_XPATH, tag, value, text))).shouldBe(Condition.visible);
        }
    }

    public void validateUploadedAttachment(String value, String attachmentTitle) {
        log.info("'{}' field contains '{}' title", value, attachmentTitle);
        if (attachmentTitle != null) {
            $(By.xpath(String.format(TC_DETAILS_LOADED_ATTACHMENT_XPATH, value, attachmentTitle))).shouldBe(Condition.visible);
        }
    }


    public void validatePropertiesTabFields(String tag, String value, String text) {
        log.info("'{}' field contains '{}' text", value, text);
        if (text != null) {
            $(By.xpath(String.format(TC_DETAILS_GENERAL_XPATH, tag, value, text))).shouldBe(Condition.visible);
        }
    }
}

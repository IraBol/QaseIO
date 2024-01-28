package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TextArea {

    private static final String TC_TEXT_AREA_XPATH = "//label[text()='%s']/following-sibling::div//div[@class='toastui-editor-ww-container']//p[contains(@data-placeholder,'For example' )]";

    public void write(String label, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(String.format(TC_TEXT_AREA_XPATH, label))).sendKeys(text);
        }
    }
}

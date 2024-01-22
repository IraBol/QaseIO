package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
@Log4j2
public class RadioButton {

    private final String TC_RADIO_BUTTON_XPATH = "//label[text()='%s']/../following-sibling::*//span[text()='%s']";

    public void setRadioButtonValue(String label, String option) {
        log.info("Select '{}' radio button inside '{}' ", option, label);
        if (option != null) {
            $(By.xpath(String.format(TC_RADIO_BUTTON_XPATH, label, option))).click();
        }
    }
}

//label[text()='Project access type']/../following-sibling::*//span[text()='Private']



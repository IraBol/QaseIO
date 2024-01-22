package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
@Log4j2
public class CheckBox {

    private final String TC_CHECKBOX_XPATH = "//label[text()='%s']/following-sibling::*//input[contains(@id,'%s')]";

    public void selectCheckBoxOption(String label, String checkBoxId, boolean isChecked) {
        log.info("Check '{}' checkbox", label);
        if (isChecked == false) {
            $(By.xpath(String.format(TC_CHECKBOX_XPATH, label, checkBoxId))).click();
        }
    }
}
//label[text()='To be automated']/following-sibling::*//input[contains(@id,'ToBeAutomated')]
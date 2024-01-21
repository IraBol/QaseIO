package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
@Log4j2
public class CheckBox {

    private final String CHECKBOX_XPATH = "//label[text()='%s']/following-sibling::*//input[contains(@id,'%s')]";

    public void selectCheckBoxOption(String label, String option) {
        log.info("Select '{}' option inside '{}' checkbox", option, label);
        if (option != null) {
            $(By.xpath(String.format(CHECKBOX_XPATH, label, option))).click();
        }
    }
}
//label[text()='To be automated']/following-sibling::*//input[contains(@id,'ToBeAutomated')]
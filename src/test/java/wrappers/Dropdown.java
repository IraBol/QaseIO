/*
Селекторы Марата

private static final String TYPE_SELECT_XPATH = "//label[text()='Type']//following::input";
private static final String TYPE_OPTION_XPATH = "(//*[@id='modals']/*)[last()]//*[text()='%s']";
 */
package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Dropdown {

    private final String TC_DROPDOWN_XPATH = "//label[text()='%s']//following::div";
    private final String TC_DROPDOWN_OPTION_XPATH = "//div[text()='%s']";

    public void setDropdownValue(String label, String option) {
        log.info("Select '{}' option inside '{}' dropdown", option, label);
        if (option != null) {
            $(By.xpath(String.format(TC_DROPDOWN_XPATH, label))).click();
            $(By.xpath(String.format(TC_DROPDOWN_OPTION_XPATH, option))).click();
        }
    }
}


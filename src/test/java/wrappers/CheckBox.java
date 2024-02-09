package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class CheckBox {

    private final String TC_CHECKBOX_XPATH = "//label[text()='%s']/following-sibling::*//input[@type='checkbox']";

    public void selectCheckBoxOption(String label, boolean isChecked) {
        log.info("Check '{}' checkbox", label);
        if (!isChecked) {
            $(By.xpath(String.format(TC_CHECKBOX_XPATH, label))).click();
        }
    }

    public void validatePropertiesTabCheckBoxStatus(String label, boolean isChecked) {
        if (isChecked) {
            Assert.assertTrue($(By.xpath(String.format(TC_CHECKBOX_XPATH, label))).isSelected(),
                    "Checkbox is not selected");
        } else {
            Assert.assertFalse($(By.xpath(String.format(TC_CHECKBOX_XPATH, label))).isSelected(),
                    "Checkbox is selected");
        }
    }
}
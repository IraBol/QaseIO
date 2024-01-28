package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class CheckBox {

    //_________________________________________CREATE TEST CASE PAGE__________________________________________________\\
    private final String TC_CHECKBOX_XPATH = "//label[text()='%s']/following-sibling::*//input[@type='checkbox']";

    public void selectCheckBoxOption(String label, boolean isChecked) {
        log.info("Check '{}' checkbox", label);
        if (isChecked == false) {
            $(By.xpath(String.format(TC_CHECKBOX_XPATH, label))).click();
        }
    }

    //_________________________________________TEST CASE DETAILS PAGE__________________________________________________\\
    public void validateCheckBoxStatus(String label, boolean isChecked) {
        if (isChecked == true) {
            Assert.assertTrue($(By.xpath(String.format(TC_CHECKBOX_XPATH, label))).isSelected(),
                    "Checkbox is not selected");
        } else if (isChecked == false) {
            Assert.assertFalse($(By.xpath(String.format(TC_CHECKBOX_XPATH, label))).isSelected(),
                    "Checkbox is selected");
        }
    }
}
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
public class DropDown {

    private final String TC_DROPDOWN_XPATH = "//label[text()='%s']//following::div";
    private final String TC_DROPDOWN_OPTION_XPATH = "//div[text()='%s']";
    private final String TC_MILESTONE_DROPDOWN_XPATH = "//div[@id='milestoneGroup']//following::div";
    private final String TC_MILESTONE_DROPDOWN_OPTION_XPATH = "//div[@id='modals']/following-sibling::div//*[text()='%s']";
    private final String TC_SUITE_DROPDOWN_XPATH = "//label[text()='Suite']/following-sibling::div";
    private final String TC_SUITE_DROPDOWN_OPTION_XPATH = "//label[text()='Suite']/following-sibling::div//*[text()='%s']";
    private final String TC_STEPS_DROPDOWN_XPATH = "//div[text()='Test Case Steps']/div";
    private final String TC_STEPS_DROPDOWN_OPTION_XPATH = "//div[text()='Test Case Steps']/div//*[text()='%s']";

    public void setDropdownValue(String label, String option) {
        log.info("Select '{}' option inside '{}' dropdown", option, label);
        if (option != null) {
            $(By.xpath(String.format(TC_DROPDOWN_XPATH, label))).click();
            $(By.xpath(String.format(TC_DROPDOWN_OPTION_XPATH, option))).click();
        }
    }

    public void setMilestoneDropdownValue(String option) {
        log.info("Select '{}' option inside milestone dropdown", option);
        if (option != null) {
            $(By.xpath(String.format(TC_MILESTONE_DROPDOWN_XPATH))).click();
            $(By.xpath(String.format(TC_MILESTONE_DROPDOWN_OPTION_XPATH, option))).click();
        }
    }

    public void setSuiteDropdownValue(String option) {
        log.info("Select '{}' option inside suite dropdown", option);
        if (option != null) {
            $(By.xpath(String.format(TC_SUITE_DROPDOWN_XPATH))).click();
            $(By.xpath(String.format(TC_SUITE_DROPDOWN_OPTION_XPATH, option))).click();
        }
    }

    public void setTestCaseStepsDropdownValue(String option) {
        log.info("Select '{}' option inside suite dropdown", option);
        if (option != null) {
            $(By.xpath(String.format(TC_STEPS_DROPDOWN_XPATH))).click();
            $(By.xpath(String.format(TC_STEPS_DROPDOWN_OPTION_XPATH, option))).click();
        }
    }
}
//div[@id='milestoneGroup']//following::div[text()='Not set'] - milestone DD
//div[@id='modals']/following-sibling::div//*[text()='%s'] - milestone DD option

//label[text()='Suite']/following-sibling::div//*[text()='Test cases without suite'] - suite DD
//label[text()='Suite']/following-sibling::div//*[text()='%s'] - suite DD option

//div[text()='Test Case Steps']/div//*[text()='Classic'] - TC steps DD
//div[text()='Test Case Steps']/div//*[text()='%s'] - TC steps DD option
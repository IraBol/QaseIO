/*
Селекторы Марата

private static final String TYPE_SELECT_XPATH = "//label[text()='Type']//following::input";
private static final String TYPE_OPTION_XPATH = "(//*[@id='modals']/*)[last()]//*[text()='%s']";
 */
package wrappers;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

@Log4j2
public class Dropdown {

    private final String TC_DROPDOWN_XPATH = "//label[text()='%s']//following::div";
    private final String TC_DROPDOWN_OPTION_XPATH = "//div[text()='%s']";
    private final String TC_MILESTONE_DROPDOWN_XPATH = "//div[@id='milestoneGroup']//following::div";
    private final String TC_MILESTONE_DROPDOWN_OPTION_XPATH = "//div[@id='modals']/following-sibling::div//*[text()='%s']";
    private final String TC_SUITE_DROPDOWN_XPATH = "//label[text()='Suite']/following-sibling::div";
    private final String TC_SUITE_DROPDOWN_OPTION_XPATH = "//label[text()='Suite']/following-sibling::div//*[text()='%s']";
    private final String TC_TAGS_XPATH = "//div[text()='%s']/following-sibling::div[@class='row']//div[text()='Select...']";
    private final String TC_TAGS_OPTION_XPATH = "//div[text()='%s']/following-sibling::div[@class='row']//*[text()='%s']";
    private final String TC_STEPS_DROPDOWN_XPATH = "//div[text()='Test Case Steps']/div";
    private final String TC_STEPS_DROPDOWN_OPTION_XPATH = "//div[text()='Test Case Steps']/div//*[text()='%s']";
    private static final String TC_GHERKIN_ADD_STEP_BUTTON_ID = "gherkin-add-step-btn";
    private static final String TC_GHERKIN_STEPS_DROPDOWN_XPATH = "//div[text()='%s']/following::tr//div[text()='%s']/following::div";
    private static final String TC_GHERKIN_STEPS_DROPDOWN_OPTION_XPATH = "//div[text()='%s']/following::tr//div[text()='%s']/following::div[text()='%s']";

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

    public void setTagsDropdownValue(String label, String... options) {
        log.info("Select '{}' option inside '{}' dropdown", options, label);
        if (options != null) {
            for (String option : options) {
                $(By.xpath(String.format(TC_TAGS_XPATH, label))).click();
                actions().click($(By.xpath(String.format(TC_TAGS_OPTION_XPATH, label, option)))).perform();
            }
        }
    }

    public void setTestCaseStepsDropdownValue(String option) {
        log.info("Select '{}' option inside test case steps dropdown", option);
        if (option != null) {
            $(By.xpath(String.format(TC_STEPS_DROPDOWN_XPATH))).click();
            $(By.xpath(String.format(TC_STEPS_DROPDOWN_OPTION_XPATH, option))).click();
        }
    }

    public void setGherkinStepsDropdownValue(String label, String inputNumber, String option) {
        log.info("Select '{}' option inside gherkin steps dropdown", option);
        if (option != null) {
            $(By.id(TC_GHERKIN_ADD_STEP_BUTTON_ID)).click();
            $(By.xpath(String.format(TC_GHERKIN_STEPS_DROPDOWN_XPATH, label, inputNumber))).click();
            $(By.xpath(String.format(TC_GHERKIN_STEPS_DROPDOWN_OPTION_XPATH, label, inputNumber, option))).shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        }
    }
}


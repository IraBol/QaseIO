package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
@Log4j2
public class Button {

    private final String BUTTON = "//button[@id='%s']/span[text()='%s']";

    public void clickButton(String buttonId, String title) {
        log.info("Click '{}' button", title);
            $(By.xpath(String.format(BUTTON, buttonId, title))).click();
    }

    //button[@id='createButton']/span[text()='Create new project']
}

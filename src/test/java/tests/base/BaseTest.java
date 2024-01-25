/*
Код с комментариями

 @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://app.qase.io";

        //если нужно что-то по-старинке от selenium
        open();
        getWebDriver().manage().window().maximize();

        //или можно так, если каких-то настроек от selenide не хватает
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        //и запихиваем этот driver в метод selenide setWebDriver
        setWebDriver(driver);


        faker = new Faker();
        loginPage = new LoginPage();
        projectsListPage = new ProjectsListPage();

        USERNAME = System.getProperty("user", PropertyReader.getProperty("qase.login"));
        System.out.println(USERNAME);
        PASSWORD = System.getProperty("password", PropertyReader.getProperty("qase.password"));
        System.out.println(PASSWORD);
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        closeWebDriver();
//        Configuration.holdBrowserOpen = false;
//        if (getWebDriver() != null) {
//            getWebDriver().quit();
//        }
 */
package tests.base;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreateCasePage;
import pages.LoginPage;
import pages.ProjectDetailsPage;
import pages.ProjectsListPage;
import tests.CreateCaseTest;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class BaseTest {

    protected static String USERNAME;
    protected static String PASSWORD;
    protected LoginPage loginPage;
    protected ProjectsListPage projectsListPage;
    protected ProjectDetailsPage projectDetailsPage;
    protected CreateCasePage createCasePage;
    protected Faker faker;

    @BeforeMethod
    public void setup() {
        log.info("Open chrome browser");

        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 10000;

        open();

        getWebDriver().
                manage().
                window().
                maximize();

        faker = new Faker();
        loginPage = new LoginPage();
        projectsListPage = new ProjectsListPage();
        projectDetailsPage = new ProjectDetailsPage();
        createCasePage = new CreateCasePage();

        USERNAME = System.getProperty("user", PropertyReader.getProperty("qase.login"));
        System.out.println(USERNAME);
        PASSWORD = System.getProperty("password", PropertyReader.getProperty("qase.password"));
        System.out.println(PASSWORD);

        log.info("Login w/ correct credentials");
        loginPage.
                openPage("/login").
                isPageOpened().
                fillOutLoginForm(USERNAME, PASSWORD).
                clickSignInButton();

        projectsListPage.
                isPageOpened();
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        log.info("Close browser");
        closeWebDriver();
    }
}

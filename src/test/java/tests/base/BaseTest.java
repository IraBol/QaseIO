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

import adapters.ProjectAdapter;
import adapters.SuiteAdapter;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.*;
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
    protected CaseDetailsPage caseDetailsPage;
    protected ProjectAdapter projectAdapter;
    protected SuiteAdapter suiteAdapter;
    protected Faker faker;

    //    @BeforeSuite(description = "Clean project list before launching tests")
//    public void deleteAllProjects() {
//        ArrayList<Project> projects = projectAdapter.getAllProjects().getEntities();
//            for (Project project : projects) {
//                projectAdapter.deleteProjectByCode(project.getCode());
//            }
//        }

    @Parameters({"browser"})
    @BeforeMethod(description = "Set up browser configurations")
    public void setup(@Optional("chrome") String browser) {

        log.info("Opening browser: '{}'", browser);

        Configuration.headless = false;
        Configuration.timeout = 10000;

        if (browser.equalsIgnoreCase("chrome")) {
            Configuration.browser = "chrome";
        } else if (browser.equals("firefox")) {
            Configuration.browser = "firefox";
        } else {
            throw new IllegalArgumentException(browser + " is not recognized");
        }

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
        caseDetailsPage = new CaseDetailsPage();
        projectAdapter = new ProjectAdapter();
        suiteAdapter = new SuiteAdapter();

        USERNAME = System.getProperty("user", PropertyReader.getProperty("qase.login"));
        PASSWORD = System.getProperty("password", PropertyReader.getProperty("qase.password"));
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        log.info("Close browser");
        closeWebDriver();
    }
}

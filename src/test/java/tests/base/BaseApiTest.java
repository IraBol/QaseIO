package tests.base;

import adapters.ProjectAdapter;
import adapters.SuiteAdapter;
import com.github.javafaker.Faker;
import dto.Project;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.PropertyReader;

import java.util.ArrayList;

public class BaseApiTest {

    protected static String API_BASE_URL;
    protected static String TOKEN;
    protected ProjectAdapter projectAdapter;
    protected SuiteAdapter suiteAdapter;
    Faker faker;

//    @BeforeSuite(description = "Clean project list before launching tests")
//    public void deleteAllProjects() {
//        ArrayList<Project> projects = projectAdapter.getAllProjects().getEntities();
//            for (Project project : projects) {
//                projectAdapter.deleteProjectByCode(project.getCode());
//            }
//        }

    @BeforeMethod(description = "Set up API configurations")
    public void setup() {

        API_BASE_URL = System.getProperty("url", PropertyReader.getProperty("qase.api.base.url"));
        TOKEN = System.getProperty("token", PropertyReader.getProperty("qase.api.token"));

        //cookies
        //rest-assured common steps - in base api page/test

        projectAdapter = new ProjectAdapter();
        suiteAdapter = new SuiteAdapter();
        faker = new Faker();
    }
}

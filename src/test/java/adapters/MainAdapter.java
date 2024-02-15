package adapters;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public abstract class MainAdapter {

    RequestSpecification spec;
    protected final static String BASE_API_URL = System.getProperty("url", PropertyReader.getProperty("qase.api.url"));
    protected static String TOKEN = System.getProperty("token", PropertyReader.getProperty("qase.api.token"));
    ;

    public MainAdapter() {
        setup();
    }

    public void setup() {

        spec = given()
                .header("Token", TOKEN)
                .contentType(ContentType.JSON);
    }
}

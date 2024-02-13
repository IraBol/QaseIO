package adapters;

import dto.Suite;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class SuiteAdapter extends MainAdapter{

    public void createNewSuite(Suite suite, String code) {

        given()
                .spec(spec)
                .body(suite)
                .log()
                .all()
                .when()
                .baseUri(BASE_API_URL)
                .post("/suite/" + code)
                .then()
                .log()
                .all()
                .statusCode(200);
    }
}

/*

Метод с учетом return type
public String createNewProject(Project project) {

        given()
                .body(project)
                .header("Token", "50da5f3f31620d1585a6352f1999b17f4cc3e74fff66587ff186a4532cb4f31d")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post("https://api.qase.io/v1/project")
                .then()
                .log().all()
                .statusCode(200);
        return project.getCode();
    }
Нужно разобраться как валидировать отдельные поля
пока не работает

    public Project validateCreatedProjectFields(String code, Project project) {
        ProjectApiResponse response = given()
                .header("Token", "50da5f3f31620d1585a6352f1999b17f4cc3e74fff66587ff186a4532cb4f31d")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("https://api.qase.io/v1/project/" + code)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .as(ProjectApiResponse.class);
        return response.getResult();

//        assert response.getResult().getTitle().equals("bla");

// WHEN
//        .when()
//                .post("/v1/create")
//
//                // THEN
//                .then()
//                .assertThat().statusCode(200)
//                .body("status", equalTo("success"))
//                .body("data.name", equalTo("GsonTest"))
//                .body("data.salary", equalTo(50000))
//                .body("data.age", equalTo(25))
//                .body("message", equalTo("Successfully! Record has been added."))
//                .log().body();
 */

package adapters;

import dto.Project;
import dto.api.response.ProjectApiResponse;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static io.restassured.RestAssured.given;

@Log4j2
public class ProjectAdapter extends MainAdapter {
    @Step("Create project with title = '{project.title}' and code = '{project.code}'")
    public void createNewProject(Project project) {
        log.info("Create project with title = '{}' and code = '{}'", project.getTitle(), project.getCode());

        given()
                .spec(spec)
                .body(project)
                .log()
                .all()
                .when()
                .baseUri(BASE_API_URL)
                .post("/project")
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200);
    }

    @Step("Get project with code = '{code}'")
    public Project getProjectByCode(String code) {
        log.info("Get project with code =  '{}'", code);

        return given()
                .spec(spec)
                .log()
                .all()
                .when()
                .baseUri(BASE_API_URL)
                .get("/project/" + code)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(ProjectApiResponse.class)
                .getResult();
    }

    @Step("Delete project with code = '{code}'")
    public void deleteProjectByCode(String code) {
        log.info("Delete project with code =  '{}'", code);

        given()
                .spec(spec)
                .log()
                .all()
                .when()
                .baseUri(BASE_API_URL)
                .delete("/project/" + code)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200);
    }

    @Step("Get all projects with title = '{project.title}' and code = '{project.code}'")
    public Project getAllProjects() {
        log.info("Get all projects");

        return given()
                .spec(spec)
                .log()
                .all()
                .when()
                .baseUri(BASE_API_URL)
                .get("/project")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(ProjectApiResponse.class)
                .getResult();
    }
}


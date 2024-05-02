package apiTests.petStory;

import api.Specification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsersTest {
    private static final String URL = "https://petstore.swagger.io/v2/user";

    @Test(priority = 1, groups = "PetStoryGroup")
    public void createUser() {
        Specification.installSpecification(Specification.responseSpecOK200(), Specification.requestSpec(URL));

        HashMap<String, String> user = new HashMap<>();
        user.put("id", "123");
        user.put("username", "Nona");
        user.put("firstName", "Nona");
        user.put("lastName", "Los");
        user.put("email", "dm1@gmail.com");
        user.put("phone", "+48123456789");
        user.put("userStatus", "123");

        Response response = given()
                .body(user)
                .when()
                .post()
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertEquals("123", message);
    }

    @Test(priority = 2, groups = "PetStoryGroup")
    public void findUser() {
        Specification.installSpecification(Specification.responseSpecOK200(), Specification.requestSpec(URL));

        Response response = given()
                .when()
                .get("/Nona")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String userName = jsonPath.get("username");
        Assert.assertEquals("Nona", userName);
    }

    @Test(priority = 3, groups = "PetStoryGroup")
    public void deleteUser() {
        Specification.installSpecification(Specification.responseSpecUnique(200), Specification.requestSpec(URL));

        given()
                .when()
                .delete("/Nona")
                .then().log().all()
                .body("message", equalTo("Nona"));
    }

}

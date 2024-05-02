package apiTests;

import api.*;
import api.exampleYoutobe1.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class RegresTest {
    private final static String URL = "https://reqres.in";

    @Test(groups = "PetStoryGroup")
    public void checkAvatarAndId() {
        Specification.installSpecification(Specification.responseSpecOK200(), Specification.requestSpec(URL));
        List<UserData> users = given()
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assert.assertTrue(users.stream().allMatch(x-> x.getEmail().endsWith("@reqres.in")));
    }

    @Test(groups = "PetStoryGroup")
    public void successRegistration() {
        Specification.installSpecification(Specification.responseSpecOK200(), Specification.requestSpec(URL));
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);

        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());
    }

    @Test(groups = "PetStoryGroup")
    public void failedRegistration() {
        Specification.installSpecification(Specification.responseSpecError400(), Specification.requestSpec(URL));

        Register user = new Register("sydney@fife", "");
        FailReg failReg = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(FailReg.class);

        Assert.assertEquals("Missing password", failReg.getError());
    }

    @Test(groups = "PetStoryGroup")
    public void sortList() {
        Specification.installSpecification(Specification.responseSpecOK200(), Specification.requestSpec(URL));
        List<ColorsData> colors = given()
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(sortedYears, years);
    }

    @Test(groups = "PetStoryGroup")
    public void checkStatusCodeAfterDeleting() {
        Specification.installSpecification(Specification.responseSpecUnique(204), Specification.requestSpec(URL));

        given()
                .when()
                .delete("/api/users/2")
                .then().log().all();
    }
}

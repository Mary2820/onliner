package apiTests.petStory;

import api.Specification;
import api.petStory.Category;
import api.petStory.Pet;
import api.petStory.SuccessCreatingPet;
import api.petStory.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PetTests {
    private static final String URL = "https://petstore.swagger.io/v2";

    @Test(priority = 1, groups = "PetStoryGroup" )
    public void checkSucceedCreatingOfPet() {
        Specification.installSpecification(Specification.responseSpecOK200(), Specification.requestSpec(URL));

        String id = "112";
        api.petStory.Category category = new Category(id, "fox");
        String name = "Alicja";
        ArrayList<String> photoUrls = new ArrayList<>();
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag(id, name));
        String status = "available";

        Pet dog = new Pet(id, category, name, photoUrls, tags, status);

        SuccessCreatingPet creatingPet = given()
                .body(dog)
                .when()
                .post("/pet")
                .then().log().all()
                .extract().as(SuccessCreatingPet.class);

        Assert.assertEquals("112", creatingPet.getId());
        Assert.assertEquals("Alicja", creatingPet.getName());

        System.out.println(getClass().getName());
    }

    @Test(priority = 2, groups = "PetStoryGroup")
    public void checkStatusOfPets() {
        Specification.installSpecification(Specification.responseSpecOK200(), Specification.requestSpec(URL));

        List<Pet> pets = given()
                .when()
                .get("/pet/findByStatus?status=pending")
                .then().log().all()
                .extract().body().jsonPath().getList("", Pet.class);


        String status = "pending";
        pets.forEach(x-> Assert.assertTrue(x.getStatus().contains(status)));
    }

    @Test(priority = 3, groups = "PetStoryGroup")
    public void findPetById() {
        Specification.installSpecification(Specification.responseSpecOK200(), Specification.requestSpec(URL));

        Pet pet = given()
                .when()
                .get("/pet/112")
                .then().log().all()
                .extract().as(Pet.class);


        String id = "112";
        Assert.assertEquals(id, pet.getId());
    }

    @Test(priority = 4, groups = "PetStoryGroup")
    public void updatePetData() {
        Specification.installSpecification(Specification.responseSpecOK200(), Specification.requestSpec(URL));

        String name = "Niko";
        String status = "sold";

        Pet dog = new Pet("112", null, name, null, null, status);

        Pet updatePet = given()
                .body(dog)
                .when()
                .put("/pet")
                .then().log().all()
                .extract().body().jsonPath().getObject("", Pet.class);

    }

    @Test(priority = 5, groups = "PetStoryGroup")
    public void deletePet() {
        Specification.installSpecification(Specification.responseSpecOK200(), Specification.requestSpec(URL));

        given()
                .when()
                .delete("/pet/112")
                .then().log().all();
    }

}

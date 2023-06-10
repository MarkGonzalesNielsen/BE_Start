package rest;

import dtos.IngredientDTO;
import entities.Ingredient;
import entities.RenameMe;
import io.restassured.http.ContentType;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class IngredientResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Ingredient r1, r2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    private static String securityToken;

    private static void login(String role, String password){
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                //.when().post("/api/login")
                .when().post("/login")
                .then()
                .extract().path("token");
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        r1 = new Ingredient("Lassagne");
        r2 = new Ingredient("Tiramisu");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Ingredient.deleteAllRows").executeUpdate();
            em.persist(r1);
            em.persist(r2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/xxx").then().statusCode(200);
    }

    @Test
    void getInfoForAll()
    {
        given()
                .contentType("application/json")
                .get("/ingredient/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                //skal stå det der står i endpointet
                .body("msg", equalTo("recipe endpoint"));
    }


    @Test
    void getAll()
    {
        List<IngredientDTO> ingredientDTOS;

        ingredientDTOS = given()
                .contentType("application/json")
                .when()
                .get("/ingredient/all")
                .then()
                .extract().body().jsonPath().getList("", IngredientDTO.class);

        IngredientDTO i1DTO = new IngredientDTO(r1);
        IngredientDTO i2DTO = new IngredientDTO(r2);
        assertThat(ingredientDTOS, containsInAnyOrder(i1DTO,i2DTO));
    }

    //virker ikke
    @Test
    public void deleteIngredient(){
        login("user","test");
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                //.header("x-access-token", securityToken)
                .delete("/ingredient/delete/{id}", r1.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(r1.getId()));
    }



    //This test assumes the database contains two rows
    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/xxx/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello World"));
    }

    @Test
    public void testCount() throws Exception {
        given()
                .contentType("application/json")
                .get("/xxx/count").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("count", equalTo(2));
    }
}

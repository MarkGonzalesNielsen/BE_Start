package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BookingDTO;
import entities.Booking;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class BookingResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Booking b1, b2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

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
        b1 = new Booking("1. Jul - 15.00","1 Hour");
        b2 = new Booking("2. Jul - 10.00","2 Hour");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Booking.deleteAllRows").executeUpdate();
            em.persist(b1);
            em.persist(b2);
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
                .get("/booking/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                //skal stå det der står i endpointet
                .body("msg", equalTo("booking endpoint"));
    }


    @Test
    void getAll()
    {
        List<BookingDTO> bookingDTOS;

        bookingDTOS = given()
                .contentType("application/json")
                .when()
                .get("/booking/all")
                .then()
                .extract().body().jsonPath().getList("", BookingDTO.class);

        BookingDTO b1DTO = new BookingDTO(b1);
        BookingDTO b2DTO = new BookingDTO(b2);
        assertThat(bookingDTOS, containsInAnyOrder(b1DTO,b2DTO));
    }


    @Test
    public void deleteIngredient(){
        login("user","test");
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                //.header("x-access-token", securityToken)
                .delete("/booking/delete/{id}", b1.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(b1.getId()));
    }

}

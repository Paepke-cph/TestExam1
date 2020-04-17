package rest;

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
        import utils.EMF_Creator.DbSelector;
        import utils.EMF_Creator.Strategy;
        import utils.Settings;

        import javax.persistence.EntityManagerFactory;
        import javax.ws.rs.core.UriBuilder;
        import java.net.URI;

        import static io.restassured.RestAssured.given;
        import static org.hamcrest.Matchers.*;

public class LoginEndpointTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testLogin_with_incorrect_password() {
        String payload ="{\"username\":\"user\",\"password\":\"blablabla\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN_403.getStatusCode())
                .body("message", equalTo("Invalid user name or password"));
    }
}
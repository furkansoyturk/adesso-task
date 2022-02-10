package com.adessotask;

import com.adessotask.service.EarthquakeService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
public class EarthquakeResourceTest {

    /*@Inject
    EarthquakeService earthquakeService;

    @Test
    public void testList() {
        given()
                .when().get("/earthquakes")
                .then()
                .statusCode(200)
                .body("$.size()", is(2),
                        "place", containsInAnyOrder("Hawaii", "NewYork"),
                        "country", containsInAnyOrder("US", "US"));
//                        "country", containsInAnyOrder("Winter fruit", "Tropical fruit"));
    }

    @Test
    public void testAdd() {
        given()
                .body("{\"place\": \"LosAngeles\", \"country\": \"US\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/earthquakes")
                .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "place", containsInAnyOrder("Hawaii", "NewYork", "LosAngeles"),
                        "country", containsInAnyOrder("US", "US", "US"));

        given()
                .body("{\"place\": \"LosAngeles\", \"country\": \"US\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .delete("/earthquakes")
                .then()
                .statusCode(200)
                .body("$.size()", is(2),
                        "place", containsInAnyOrder("Hawaii", "NewYork"),
                        "country", containsInAnyOrder("US", "US"));
    }

    @Test
    public void testService() throws IOException {
        //earthquakeService("TR",2);
    }*/
}

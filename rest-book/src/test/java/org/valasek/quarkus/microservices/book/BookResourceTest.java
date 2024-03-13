package org.valasek.quarkus.microservices.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
class BookResourceTest {
    @Test
    void shouldCreateBook() {
        given()
            .formParam("title", "Test")
            .formParam("author", "Daniel")
            .formParam("year", "2024")
            .formParam("genre", "IT")

        .when()
            .post("/api/books")
        .then()
            .statusCode(201)
            .body("isbn_13", startsWith("13-"))
            .body("title", is("Test"))
            .body("author", is("Daniel"))
            .body("year_of_publication", is(2024))
            .body("genre", is("IT"))
            .body("creation_date", startsWith("202"));
    }
}
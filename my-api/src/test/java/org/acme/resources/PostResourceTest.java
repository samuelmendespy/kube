package org.acme.resources;

import io.quarkus.test.junit.QuarkusTest;

import jakarta.ws.rs.core.Response;
import org.acme.dtos.PostCreateDTO;
import org.acme.dtos.PostUpdateDTO;
import org.acme.models.Post;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PostResourceTest {

    @Test
    public void testGetPosts() {
        given()
                .when().get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testCreatePost() {
        PostCreateDTO postCreateDTO = new PostCreateDTO();
        postCreateDTO.text = "Test Post";
        postCreateDTO.image = "http://tests.com/image.jpg";
        postCreateDTO.user = "Test User";

        given()
                .contentType("application/json")
                .body(postCreateDTO)
                .when().post("/posts")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("text", equalTo(postCreateDTO.text))
                .body("image", equalTo(postCreateDTO.image))
                .body("user", equalTo(postCreateDTO.user));
    }

    @Test
    public void testUpdatePost() {
        Post post = new Post();
        post.text = "Original Post";
        post.image = "http://tests.com/original_image.jpg";
        post.user = "Original user";
        post.persist();

        PostUpdateDTO postUpdateDTO = new PostUpdateDTO();
        postUpdateDTO.text = "Updated Post";
        postUpdateDTO.image = "http://tests.com/updated_image.jpg";
        postUpdateDTO.user = "Updated user";

        given()
                .contentType("application/json")
                .body(postUpdateDTO)
                .when().put("/posts/" + post.id)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("text", equalTo(postUpdateDTO.text))
                .body("image", equalTo(postUpdateDTO.image))
                .body("user", equalTo(postUpdateDTO.user));
    }

    @Test
    public void testDeletePost() {
        Post post = new Post();
        post.text = "Spam Post";
        post.image = "http://tests.com/spam.jpg";
        post.user = "User spamming posts";
        post.persist();

        given()
                .when().delete("/posts/" + post.id)
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void testDeletePostNotFound() {
        given()
                .when().delete("/posts/000000000000000000000000")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestUpdateInstances extends TestBase {

    @Test
    public void testUpdateInstancePutNoTitle() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO(null, false, "test");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .put("/todos/1");

        assertEquals(400, response.statusCode());
    }

    @Test
    public void testUpdateInstancePut() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO("title1", false, "");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .put("/todos/1");

        assertEquals(200, response.statusCode());

        TodoResponseDTO todo = response.jsonPath().getObject("", TodoResponseDTO.class);

        assertEquals(todoRequestDTO.getTitle(), todo.getTitle());
        assertEquals(todoRequestDTO.getDoneStatus() ? "true" : "false", todo.getDoneStatus());
        assertEquals(todoRequestDTO.getDescription(), todo.getDescription());
    }

    @Test
    public void testUpdateInstancePutNotFound() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO("title1", false, "");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .put("/todos/3");

        assertEquals(404, response.statusCode());
    }

    @Test
    public void testUpdateInstancePostNoTitle() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO(null, false, "test");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .post("/todos/1");

        System.out.println(response.asString());

        assertEquals(200, response.statusCode());

        TodoResponseDTO todo = response.jsonPath().getObject("", TodoResponseDTO.class);

        assertEquals(todoRequestDTO.getDoneStatus() ? "true" : "false", todo.getDoneStatus());
        assertEquals(todoRequestDTO.getDescription(), todo.getDescription());
    }

    @Test
    public void testUpdateInstancePost() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO("title1", false, "");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .post("/todos/1");

        assertEquals(200, response.statusCode());

        TodoResponseDTO todo = response.jsonPath().getObject("", TodoResponseDTO.class);

        assertEquals(todoRequestDTO.getTitle(), todo.getTitle());
        assertEquals(todoRequestDTO.getDoneStatus() ? "true" : "false", todo.getDoneStatus());
        assertEquals(todoRequestDTO.getDescription(), todo.getDescription());
    }

    @Test
    public void testUpdateInstancePostNotFound() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO("title1", false, "");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(todoRequestDTO)
                .post("/todos/3");

        assertEquals(404, response.statusCode());
    }
}
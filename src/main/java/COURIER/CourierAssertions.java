package COURIER;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class CourierAssertions {

    public void createdSuccessfully (ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is (true));
    }
    public int loggedInSuccessfully (ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0))
                .extract()
                .path("id");
    }
    public void creationFailed (ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    public void existingCredsCreation(ValidatableResponse response) {
        response.assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

}
package ORDER;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class OrderClient {
    private static final String ORDER_PATH = "api/v1/orders"; // создание/получение заказа
    private static final String CANCEL_ORDER_PATH = "api/v1/orders/cancel"; //отмена заказаа

    String base_url;

    public OrderClient(String base_url) {
        this.base_url = base_url;
    }

    @Step("Создание заказа")
    public ValidatableResponse createNewOrder(OrderCreat orderCreat) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(base_url)
                .body(orderCreat)
                .when()
                .post(ORDER_PATH)
                .then();
    }
    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(base_url)
                .when()
                .get(ORDER_PATH)
                .then();
    }
    @Step("Отмена заказа")
    public ValidatableResponse cancelOrder(java.lang.Object track) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(base_url)
                .body(track)
                .when()
                .put(CANCEL_ORDER_PATH)
                .then();
    }

}
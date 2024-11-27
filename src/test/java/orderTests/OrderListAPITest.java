package orderTests;

import ORDER.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import org.junit.Test;

import static COURIER.Specif.BASE_URL;
import static org.hamcrest.CoreMatchers.notNullValue;


public class OrderListAPITest {

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Успешное получение списка заказов")
    public void getOrderList() {
        OrderClient orderClient = new OrderClient(BASE_URL);
        ValidatableResponse responseOrderList = orderClient.getOrderList();
        responseOrderList.statusCode(200).and().body("orders", notNullValue());
    }

}
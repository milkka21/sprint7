package orderTests;
import ORDER.OrderClient;
import ORDER.OrderCreat;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static COURIER.Specif.BASE_URL;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderTest {
    private OrderClient orderClient;
    private final String[] color;
    java.lang.Object track;

    public OrderTest(String[] color) {
        this.color = color;
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient(BASE_URL);
    }

    @Parameterized.Parameters(name = "Цвет самоката: {0}")
    public static Object[][] getColour() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK", "GRAY"}},
                {new String[]{}}
        };
    }

    @Test
    @DisplayName("Создание заказа с разными цветами")
    @Description("Создание заказа с одним из цветов/двумя цветами/без цвета")
    public void MakeAnOrderWithDifferentColors() {
        OrderCreat orderCreat = new OrderCreat(color);
        ValidatableResponse responseCreateOrder = orderClient.createNewOrder(orderCreat);
        track = responseCreateOrder.extract().path("track");
        responseCreateOrder.assertThat()
                .statusCode(201)
                .body("track", notNullValue());
    }


    @After
    @Step("Cancel test order")
    public void CancelTestOrder() {
        orderClient.cancelOrder(track);
    }
}
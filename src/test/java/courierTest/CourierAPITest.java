package courierTest;

import COURIER.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static COURIER.Specif.BASE_URL;

public class CourierAPITest {


    protected final CourierRandom random= new CourierRandom();
    private Courier courier;
    private CourierClient courierClient;
    private CourierAssertions courierAssertions;
    int courierId;

    @Before
    @Step("Предусловия для создания курьера")
    public void setUp() {
        courierClient = new CourierClient(BASE_URL);
        courier = random.random();
        courierAssertions = new CourierAssertions();
    }

    @After
    @Step("Удаление курьера")
    public void deleteCourier () {
        if (courierId > 0) {
            courierClient.delete(courierId);
        }
    }

    @Test
    @DisplayName("Создание нового курьера")
    @Description("Курьера можно создать")
    public void courierCanBeCreated() {
        ValidatableResponse responseCreateCourier = courierClient.create(courier);
        courierAssertions.createdSuccessfully(responseCreateCourier);
        Credentials credentials = Credentials.from(courier);
        ValidatableResponse responseLoginCourier = courierClient.login(credentials);
        courierId = responseLoginCourier.extract().path("id");
    }
    @Test
    @DisplayName("Создание курьера без заполнения поля логин")
    @Description("Курьера нельзя создать без логина. Проверяем статус код и сообщение об ошибке")
    public void courierCanNotBeCreatedWithoutLogin() {
        courier.setLogin(null);
        ValidatableResponse responseNullLogin = courierClient.create(courier);
        courierAssertions.creationFailed(responseNullLogin);
    }


    @Test
    @DisplayName("Создание курьера без заполнения поля пароля")
    @Description("Курьера нельзя создать без пароля. Проверяем статус код и сообщение об ошибке")
    public void courierCanNotBeCreatedWithoutPassword() {
        courier.setPassword(null);
        ValidatableResponse responseNullPassword = courierClient.create(courier);
        courierAssertions.creationFailed(responseNullPassword);
    }
    @Test
    @DisplayName("Создание курьера без заполнения поля пароля и логина")
    @Description("Курьера нельзя создать без пароля и логина. Проверяем статус код и сообщение об ошибке")
    public void courierCanNotBeCreatedWithoutLoginAndPassword() {
        courier.setLogin(null);
        courier.setPassword(null);
        ValidatableResponse responseNullFields = courierClient.create(courier);
        courierAssertions.creationFailed(responseNullFields);
    }
    @Test
    @DisplayName("Создание курьера с существующими данными")
    @Description("Создание курьера с существующими данными. Проверяем статус код и сообщение.")
    public void courierCanNotBeCreatedWithExistingCreds() {
        courierClient.create(courier);
        ValidatableResponse responseCreateCourier = courierClient.create(courier);
        courierAssertions.existingCredsCreation(responseCreateCourier);
    }


}
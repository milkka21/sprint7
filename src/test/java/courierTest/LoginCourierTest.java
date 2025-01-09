package courierTest;
import COURIER.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;

import static COURIER.Specif.BASE_URL;
import static org.hamcrest.CoreMatchers.equalTo;


public class LoginCourierTest {
    private CourierRandom courierRandom = new CourierRandom();
    private CourierClient courierClient;
    private Courier courier;
    CourierAssertions courierAssertions;
    int idCourier;
    @Before
    @Step("Предусловия для логина курьера")
    public void setUp() {
        courierClient = new CourierClient(BASE_URL);
        courier = courierRandom.random();
        courierAssertions = new CourierAssertions();
    }
    @Test
    @DisplayName("Логин курьера существующими данными")
    @Description("Можно войти с существующими данными")
    public void courierCanSuccessfullyLogin() {
        courierClient.create(courier);
        Credentials credentials = Credentials.from(courier);
        
        ValidatableResponse responseLoginCourier = courierClient.login(credentials);
        courierAssertions.loggedInSuccessfully(responseLoginCourier);
        idCourier = responseLoginCourier.extract().path("id");
    }

    @Test
    @DisplayName("Логин курьера без заполнения поля Логин")
    @Description("Попытка входа без заполнения поля Логин. Проверка сообщения об ошибке")
    public void courierLoginUnsuccessfullyWithoutLogin() {
        Credentials credentialsWithoutLogin = new Credentials("", courier.getPassword()); // c null тесты виснут
        ValidatableResponse responseLoginErrorMessage = courierClient.login(credentialsWithoutLogin).statusCode(400);
        responseLoginErrorMessage.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин курьера без ввода пароля")
    @Description("Попытка входа без ввода пароля. Проверка сообщения об ошибке")
    public void courierLoginUnsuccessfullyWithoutPassword() {
        Credentials credentialsWithoutLogin = new Credentials(courier.getLogin(), "");
        ValidatableResponse responsePasswordErrorMessage = courierClient.login(credentialsWithoutLogin).statusCode(400);
        responsePasswordErrorMessage.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин курьера без ввода логина И пароля")
    @Description("Попытка входа без ввода логина И пароля. Проверка сообщения об ошибке")
    public void courierLoginWithoutLoginAndPassword() {
        Credentials credentialsWithoutLoginAndPassword = new Credentials("", "");
        ValidatableResponse responseWithoutLoginAndPasswordMessage = courierClient.login(credentialsWithoutLoginAndPassword).statusCode(400);
        responseWithoutLoginAndPasswordMessage.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин курьера под несуществующим логином")
    @Description("Попытка входа с несуществующим логином. Проверка сообщения об ошибке")
    public void courierLoginWithNotExistingLogin() {
        Credentials credentialsWithNotExistingLogin = new Credentials(RandomStringUtils.randomAlphanumeric(6), courier.getPassword());
        ValidatableResponse responseWithWithNotExistingLoginMessage = courierClient.login(credentialsWithNotExistingLogin).statusCode(404);
        responseWithWithNotExistingLoginMessage.assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    @Step("Удаление курьера")
    public void deleteCourier() {
        if (idCourier != 0) {
            courierClient.delete(idCourier);
        }
    }

}
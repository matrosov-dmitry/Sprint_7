package ru.samokat.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class CourierSteps
    {
        CourierHTTPClient client = new CourierHTTPClient ();

        @Step("Создание курьера")
        protected ValidatableResponse courierCreating(String login, String password, String firstName) {
            Courier courier = new Courier (login, password, firstName);
            return client.createCourier (courier);
        }

        @Step("Логин курьера")
        protected ValidatableResponse courierLogging(String login, String password) {
            Courier courier = new Courier (login, password, null);
            return client.loginCourier (courier);
        }

        @Step("Получение ID курьера")
        protected String getIdCourier(String login, String password) {
            return courierLogging (login, password).extract ().path ("id").toString ();
        }

        @Step("Удаление курьера")
        protected void courierDeleting(String login, String password) {
            client.deleteCourier (getIdCourier (login, password));
        }
    }

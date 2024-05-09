package ru.samokat.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderSteps
    {
        OrderHTTPClient client = new OrderHTTPClient ();

        @Step("Создание заказа")
        protected ValidatableResponse orderCreating(Order order) {
            return client.createOrder (order);
        }

        @Step("Получение списка заказов")
        protected ValidatableResponse orderList( ) {
            return client.getOrderList ();
        }

        @Step("Отмена заказа")
        protected ValidatableResponse orderCancelling(int trackNumber) {
            return client.cancelOrder (trackNumber);
        }

        @Step("Получение заказа по его номеру")
        protected ValidatableResponse orderData(int trackNumber) {
            return client.getOrderData (trackNumber);
        }

        @Step("Получение трек-номера заказа")
        protected int getTrackNumber(ValidatableResponse response) {
            return response.extract ().path ("track");

        }
    }

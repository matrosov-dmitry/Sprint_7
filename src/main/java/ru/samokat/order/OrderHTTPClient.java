package ru.samokat.order;

import io.restassured.response.ValidatableResponse;
import ru.samokat.HTTPClient;

import static constants.Constants.*;

public class OrderHTTPClient extends HTTPClient
    {
        public ValidatableResponse createOrder(Order order) {
            return postRequest (CREATE_ORDER_REQUEST, order)
                    .then ().log ().all ();
        }

        public ValidatableResponse getOrderList( ) {
            return getRequest (GET_ORDERS_LIST)
                    .then ().log ().all ();
        }

        public ValidatableResponse cancelOrder(int trackNumber) {
            return putRequest (CANCEL_ORDER + trackNumber)
                    .then ().log ().all ();
        }

        public ValidatableResponse getOrderData(int trackNumber) {
            return getRequest (GET_ORDER_BY_TRACK + trackNumber)
                    .then ().log ().all ();
        }
    }

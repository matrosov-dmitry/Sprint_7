package ru.samokat.courier;

import io.restassured.response.ValidatableResponse;
import ru.samokat.HTTPClient;

import static constants.Constants.*;

public class CourierHTTPClient extends HTTPClient
    {
        public ValidatableResponse createCourier(Courier courier) {
            return postRequest (COURIER_CREATE_REQUEST, courier)
                    .then ().log ().all ();

        }

        public ValidatableResponse loginCourier(Courier courier) {
            return postRequest (COURIER_LOGIN_REQUEST, courier)
                    .then ().log ().all ();
        }

        public ValidatableResponse deleteCourier(String idCourier) {
            return deleteRequest (DELETE_REQUEST + idCourier)
                    .then ().log ().all ();
        }

    }

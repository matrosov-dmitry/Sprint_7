package ru.samokat.order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.notNullValue;

public class TestOrderList
    {
        OrderSteps step;


        @Test
        @DisplayName("Получение списка заказов.")
        public void testOrderList( ) {
            step = new OrderSteps ();
            step.orderList ()
                    .assertThat ()
                    .statusCode (HTTP_OK)
                    .and ()
                    .assertThat ().body ("orders", notNullValue ());

        }
    }

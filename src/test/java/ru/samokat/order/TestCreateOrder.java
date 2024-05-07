package ru.samokat.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class TestCreateOrder
    {
        Order order;
        OrderSteps step;
        int trackNumber;
        private String firstName;
        private String lastName;
        private String address;
        private String metroStation;
        private String phone;
        private int rentTime;
        private String deliveryDate;
        private String comment;
        private List<String> color;

        public TestCreateOrder(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.metroStation = metroStation;
            this.phone = phone;
            this.rentTime = rentTime;
            this.deliveryDate = deliveryDate;
            this.comment = comment;
            this.color = color;
        }

        @Parameterized.Parameters(name = "Тестовые данные, цвет:{8}")
        public static Object[][] getData( ) {
            return new Object[][]{
                    {"Дмитрий", "Матросов", "Москва, Андроньевская", "34", "+79161233333", 2, "2024-05-07", "Побыстрей", List.of ("GREY")},
                    {"Дмитрий", "Матросов", "Москва, Андроньевская", "34", "+79161233333", 2, "2024-05-07", "Побыстрей", List.of ("BLACK")},
                    {"Дмитрий", "Матросов", "Москва, Андроньевская", "34", "+79161233333", 2, "2024-05-07", "Побыстрей", Arrays.asList ("BLACK", "GREY")},
                    {"Дмитрий", "Матросов", "Москва, Андроньевская", "34", "+79161233333", 2, "2024-05-07", "Побыстрей", null}
            };
        }

        @Before
        public void setUp( ) {
            step = new OrderSteps ();
            order = new Order (firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        }

        @Test
        @DisplayName("Создание заказа")
        @Description("Проверка возможности создания заказа")
        public void testCreateOrder( ) {
            ValidatableResponse response = step.orderCreating (order)
                    .assertThat ()
                    .statusCode (HTTP_CREATED)
                    .and ()
                    .assertThat ().body ("track", notNullValue ());
            trackNumber = step.getTrackNumber (response);
        }

        @After
        public void orderCancel( ) {
            step.orderCancelling (trackNumber);
        }
    }


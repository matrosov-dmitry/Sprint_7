package ru.samokat.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class TestLoginCourier
    {
        String login = RandomStringUtils.randomAlphabetic (4, 10);
        String nonexistentLogin = RandomStringUtils.randomAlphabetic (4, 10);
        String password = RandomStringUtils.randomAlphabetic (4, 10);
        String firstName = RandomStringUtils.randomAlphabetic (4, 10);
        CourierSteps step;

        @Before
        public void setUp( ) {
            step = new CourierSteps ();
            step.courierCreating (login, password, firstName);
        }

        @Test
        @DisplayName("Логин курьера")
        @Description("Проверка - курьер может авторизоваться, ")
        public void testLoginCourier( ) {
            step.courierLogging (login, password)
                    .assertThat ()
                    .statusCode (HTTP_OK)
                    .and ()
                    .assertThat ().body ("id", notNullValue ());
        }

        @Test
        @DisplayName("Логин курьера")
        @Description("Проверка - если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
        public void testLoginCourierNonexistentLogin( ) {
            step.courierLogging (nonexistentLogin, password)
                    .assertThat ()
                    .statusCode (HTTP_NOT_FOUND)
                    .and ()
                    .assertThat ().body ("message", equalTo ("Учетная запись не найдена"));
        }

        @Test
        @DisplayName("Логин курьера")
        @Description("Проверка - если какого-то поля нет, запрос возвращает ошибку")
        public void testLoginCourierWithoutLogin( ) {
            step.courierLogging (null, password)
                    .assertThat ()
                    .statusCode (HTTP_BAD_REQUEST)
                    .and ()
                    .assertThat ().body ("message", equalTo ("Недостаточно данных для входа"));
        }

        @After
        public void deleteCourier( ) {
            try {
                step.courierDeleting (login, password);
            } catch (NullPointerException e) {
                System.out.println ("Нельзя удалить несуществующего курьера");
            }
        }
    }

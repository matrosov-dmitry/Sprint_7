package ru.samokat.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;

public class TestCreateCourier
    {
        String login = RandomStringUtils.randomAlphabetic (4, 10);
        String password = RandomStringUtils.randomAlphabetic (4, 10);
        String firstName = RandomStringUtils.randomAlphabetic (4, 10);
        CourierSteps step;

        @Before
        public void setUp( ) {
            step = new CourierSteps ();
        }

        @Test
        @DisplayName("Создание курьера")
        @Description("Проверка возможности создания курьера")
        public void testCreateCourier( ) {
            step.courierCreating (login, password, firstName)
                    .assertThat ()
                    .statusCode (HTTP_CREATED)
                    .and ()
                    .assertThat ().body ("ok", equalTo (true));
        }

        @Test
        @DisplayName("Создание курьера")
        @Description("Проверка невозможности создать двух одинаковых курьеров")
        public void testNotCreateDuplicate( ) {
            step.courierCreating (login, password, firstName);
            step.courierCreating (login, password, firstName)
                    .assertThat ()
                    .statusCode (HTTP_CONFLICT)
                    .and ()
                    .assertThat ().body ("message", equalTo ("Этот логин уже используется."));
        }

        @Test
        @DisplayName("Создание курьера")
        @Description("Проверка если одного из полей нет, запрос возвращает ошибку")
        public void testCreateCourierWithMissedFields( ) {
            step.courierCreating (null, password, firstName)
                    .assertThat ()
                    .statusCode (HTTP_BAD_REQUEST)
                    .and ()
                    .assertThat ()
                    .body ("message", equalTo ("Недостаточно данных для создания учетной записи"));
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

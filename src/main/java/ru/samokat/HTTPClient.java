package ru.samokat;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static constants.Constants.SAMOKAT_URL;
import static io.restassured.RestAssured.given;

public class HTTPClient
    {
        @Step("RequestSpecification")
        protected RequestSpecification requestSpecification( ) {
            return given ().log ().all ()
                    .contentType (ContentType.JSON)
                    .baseUri (SAMOKAT_URL);
        }

        protected Response postRequest(String path, Object body) {
            return given ()
                    .spec (requestSpecification ())
                    .body (body)
                    .post (path);
        }

        protected Response deleteRequest(String path) {
            return given ()
                    .spec (requestSpecification ())
                    .delete (path);
        }

        protected Response getRequest(String path) {
            return given ()
                    .spec (requestSpecification ())
                    .get (path);
        }

        protected Response putRequest(String path) {
            return given ()
                    .spec (requestSpecification ())
                    .put (path);
        }
    }

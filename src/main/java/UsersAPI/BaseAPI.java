package UsersAPI;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static io.restassured.RestAssured.given;


public class BaseAPI {
    private static final Logger logger = LoggerFactory.getLogger(BaseAPI.class);
    protected Response getRequest(String baseUri, String endpoint, String userId) {
        try {
            String fullUrl = endpoint + "/" + userId;
            logger.info("Sending GET request to: {}{}", baseUri, fullUrl);
            Response response = RestAssured
                    .given()
                    .baseUri(baseUri)
                    .header("x-api-key","reqres-free-v1")
                    .when()
                    .get(fullUrl)
                    .then()
                    .log().ifError()
                    .extract().response();

            handleErrors(response);
            return response;
        } catch (Exception e) {
            logger.error("Get requestfailed:", e.getMessage());
            throw e;
        }
    }

    protected Response postRequest(String baseUri, String endpoint, Object body){
       try { logger.info("Sending Post request to: {}{} with body:{}",baseUri,endpoint,body);
         Response response = RestAssured
                 .given()
                .baseUri(baseUri)
                 .header("x-api-key","reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint)
                 .then()
                 .log().ifError()
                 .extract().response();

         handleErrors(response);
         return response;

       }
        catch (Exception e){
            logger.error("PostRequestfailed",e.getMessage());
            throw e;
        }
        }

    protected Response putRequest(String baseUri, String endpoint, Object body, String  userId) {
       try { logger.info("Sending PUT request to: {}{} with body: {}", baseUri, endpoint, body, userId);
           Response response = RestAssured
                   .given()
                .baseUri(baseUri)
                   .header("x-api-key","reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint + "/" +  userId)
                   .then()
                   .log().ifError()
                   .extract().response();

           handleErrors(response);
           return response;
       }
       catch (Exception e){
           logger.error("PutRequestfailed",e.getMessage());
           throw e;
       }
    }

    protected void validateStatusCode(Response response, int expectedStatus) {
        int actual = response.getStatusCode();
        logger.info("Validating response status: expected={}, actual={}", expectedStatus, actual);
        if (actual != expectedStatus) {
            throw new AssertionError("Expected status " + expectedStatus + " but got " + actual);
        }}


    @Step("handle errors")
        private static void handleErrors(Response response) {
            int statusCode = response.statusCode();
            logger.info("Actual status code: "+ statusCode);
            logger.info("The response : " + response.asString());
            if (statusCode >= 400) {
               logger.error("Api return errors"+ statusCode);
               logger.error("The response : " + response.asString());

            }
}}

package UsersAPI;

import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.response.Response;
import utils.JsonUtils;
import utils.Validations;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class UsersAPI extends BaseAPI {


    public static String createId;
    public Response createUser(String baseUri, String endPoint, String body){
        Response response=  postRequest(baseUri,endPoint,body);
//        Map<String, String> actual = JsonUtils.parseJsonToMap(response.getBody().asString());
//        Map<String, String> expected = JsonUtils.parseJsonToMap(body);
//
//        actual.remove("id");
//        actual.remove("createdAt");
        createId = response.path("id");


        Validations.validationTrue(response.getStatusCode()==201,"Expected status code 201");
        return response;

    }

    public Response retrieveUser( String baseUri, String endPoint, String userId){
        Response response = getRequest(baseUri,endPoint, userId);
        Validations.validationTrue(response.getStatusCode()==200,"Expected status code 200");


        return response ;
    }

    public Response updateUSer (String baseUri, String endPoint, String body, String userId) {
        Response response = putRequest(baseUri,endPoint,body , userId);
        Map<String,String>  actual= JsonUtils.parseJsonToMap(response.getBody().asString());
        Map<String,String> expected= JsonUtils.parseJsonToMap(body);
        actual.remove("updatedAt");

        Validations.EqualValidation(actual,expected,"Expected response");
        return response;
    }
}

package UserInfo;

import UsersAPI.UsersAPI;
import UsersAPI.UserPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

public class UserInfo extends TestBase {
    protected String createBody, updateBody;
    protected UserPayload createUserBody, updateUserBody;

    @BeforeMethod
    public void dataSetup() throws JsonProcessingException {
        createUserBody= mapToPayload(JsonUtils.getUsersData("createUser"));
        ObjectMapper mapper = new ObjectMapper();
        createBody = mapper.writeValueAsString(createUserBody);
        updateUserBody = mapToPayload(JsonUtils.getUsersData("updateUser"));
        updateBody = mapper.writeValueAsString(updateUserBody);

    }

    @Step("Create user")
    @Test(priority = 1)
    public void createUser(){
        new UsersAPI()
                . createUser(baserUri,endPoint,createBody );
    }

    @Step("Get user by Userid")
    @Test(priority =2 , dependsOnMethods = "createUser")
    public void getUsers(){
        String UserID = UsersAPI.createId;
        new UsersAPI()
                .retrieveUser(baserUri,endPoint,UserID);
    }

    @Step("Update user")
    @Test(priority =3 , dependsOnMethods = "createUser")
    public void updateUser() {
        String UserID = UsersAPI.createId;
        new UsersAPI()
                .updateUSer(baserUri, endPoint, updateBody, UserID);

    }
}

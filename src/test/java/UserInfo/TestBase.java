package UserInfo;
import UsersAPI.UserPayload;
import UsersAPI.UsersAPI;
import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import utils.AllureUtils;
import utils.ConfigReaderUtils;
import utils.FilesUtils;
import utils.JsonUtils;

import java.io.File;
import java.util.Map;

public class TestBase {
    protected final String baserUri= ConfigReaderUtils.getProperty("baseurl");
    protected final String endPoint = ConfigReaderUtils.getProperty("endpoint");
    protected Map<String, String> createUserData;

    protected Map<String, String> updateUserData;

    File allure_reports = new File("test-outputs/allure-results");

    @BeforeSuite
    public void beforesuit(){

        FilesUtils.deleteFiles(allure_reports);
    }


    @BeforeTest
    @Step("Load Test Data")
    public void Setup(){

        JsonUtils.loadTestData();
        createUserData = JsonUtils.getUsersData("createUser");
        updateUserData = JsonUtils.getUsersData("updateUser");

    }
    @Step("Convert map  TO Payload to get data")
    public UserPayload mapToPayload(Map<String, String> data) {
        return new UserPayload(
                data.get("name"),
                data.get("job")
        );
    }

    @AfterClass
    public void afterClass(){

        AllureUtils.attacheLogsToAllureReport();
    }

    }
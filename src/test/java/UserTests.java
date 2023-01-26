import static io.restassured.RestAssured.*;
import apiBuilder.UserApisBuilder;
import apiConfigs.HeaderConfigs;
import apiConfigs.ApiPath;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UserTests extends BaseTest {
    HeaderConfigs headers = new HeaderConfigs();
    UserApisBuilder userApisBuilder = new UserApisBuilder();
    Properties props = new Properties();

    @BeforeClass
    @Description("Test Setup Before The Test Class - Read The Test Data From Properties File")
    public void readUserData() throws IOException {
        FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/data/user-data.properties");
        props.load((fileReader));
    }

    @Test
    @Description("Test Create User API")
    public void testCreateUser() {
        given().headers(headers.defaultHeaders())
               .body(userApisBuilder.postUserPayload(props.getProperty("newUserName"), props.getProperty("newUserFirstName"),
                                            props.getProperty("newUserLastName"), props.getProperty("newUserEmail"), props.getProperty("newUserPassword"),
                                            props.getProperty("newUserPhone"), Integer.parseInt(props.getProperty("newUserStatus")))
                                    .toJSONString()).when().post(ApiPath.apiPath.CREATE_USER).then().statusCode(200).log().all();
    }

    @Test
    @Description("Test Get User API After Creation Based on Dynamic User Name")
    public void testGetUser() {
        String endPoint = String.format(ApiPath.apiPath.DELETE_USER, props.getProperty("newUserName"));
        given().get(endPoint).then().statusCode(200).log().all();
    }

    @Test
    @Description("Test Update User API After Creation Based on Dynamic User Name")
    public void testUpdateUser() {
      String endPoint = String.format(ApiPath.apiPath.UPDATE_USER, props.getProperty("newUserName"));

        given().headers(headers.defaultHeaders()).body(userApisBuilder.updateUserPayload(props.getProperty("updateUserName"), props.getProperty("updateUserFirstName"),
                props.getProperty("updateUserLastName"), props.getProperty("updateUserEmail"), props.getProperty("updateUserPassword"),
                props.getProperty("updateUserPhone"), Integer.parseInt(props.getProperty("updateUserStatus"))).toJSONString()).when().put(endPoint).then().statusCode(200).log().all();
    }

    @Test(priority = 4)
    @Description("Test Delete User API After Update Based on Dynamic User Name")
    public void testDeleteUser() {
        String endPoint = String.format(ApiPath.apiPath.GET_USER, props.getProperty("updateUserName"));

        when().delete(endPoint).then().statusCode(200).log().all();
    }
}

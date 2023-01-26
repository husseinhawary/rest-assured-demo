import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.*;
public class BaseTest {

    @BeforeSuite
    @Description("Test Setup Before The Test Suite - Read The BaseURI From Properties File")
    public void setup() throws IOException {
        FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/data/testEnv.properties");
        Properties props = new Properties();
        props.load((fileReader));

        baseURI = props.getProperty("Url");
    }
}

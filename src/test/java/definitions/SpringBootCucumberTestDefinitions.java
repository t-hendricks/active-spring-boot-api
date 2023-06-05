package definitions;

import com.active.demo.ActiveApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ActiveApplication.class)
public class SpringBootCucumberTestDefinitions {

    private static final String BASE_URL = "http://localhost:";
    private static final RequestSpecification request = RestAssured.given();
    private static Response response;
    private static String generalUserToken;

    @LocalServerPort
    String port;

    public SpringBootCucumberTestDefinitions() {
        RestAssured.baseURI = BASE_URL;
    }

    @When("User registers a unique account")
    public void userRegistersAUniqueAccount() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "JohnDoe");
        requestBody.put("password", "12345");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login");
    }

    @Then("An account is created")
    public void anAccountIsCreated() {
        String msg = response.jsonPath().get("message");
        Map<String, String> user = response.jsonPath().get("data");
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals("success", msg);
        Assert.assertEquals("JognDoe", user.get("username"));
        Assert.assertEquals("12345", user.get("password"));
    }
}

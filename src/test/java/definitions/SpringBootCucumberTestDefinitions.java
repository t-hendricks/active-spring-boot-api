package definitions;

import com.active.demo.ActiveApplication;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ActiveApplication.class)
public class SpringBootCucumberTestDefinitions {

    private static final String BASE_URL = "http://localhost:";
    private static final RequestSpecification request = RestAssured.given();
    private static Response response;
    private static String userToken;

    @LocalServerPort
    String port;

    public SpringBootCucumberTestDefinitions() {
        RestAssured.baseURI = BASE_URL;
    }

    /**
     * Setting up the request header and body to invoke a POST endpoint to
     * register a new user.
     *
     * @throws Exception
     */
    @When("User registers a unique account")
    public void userRegistersAUniqueAccount() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", "JohnDoe");
        requestBody.put("password", "12345");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/register");
    }

    /**
     * Testing the results from the response to make sure
     * the new user is registered.
     */
    @Then("An account is created")
    public void anAccountIsCreated() {
        String msg = response.jsonPath().get("message");
        Map<String, String> user = response.jsonPath().get("data");

        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals("success", msg);
        Assert.assertEquals("JohnDoe", user.get("userName"));
    }

    /**
     * Setting up the request header and body to invoke a POST endpoint to
     * log in an existing user.
     *
     * @throws Exception
     */
    @When("User log in with credentials")
    public void userLogInWithCredentials() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", "JohnDoe");
        requestBody.put("password", "12345");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login");
    }

    /**
     * Testing the results from the response to make sure
     * the user exists and can log in.
     */
    @Then("The user is logged in")
    public void theUserIsLoggedIn() {
        String token = response.jsonPath().get("message");

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(String.class, token.getClass());
        userToken = token;
    }

    /**
     * Setting up the request header and body to invoke a POST endpoint to
     * create an activity for the current logged-in user.
     *
     * @throws Exception
     */
    @When("I create a new post")
    public void iCreateANewPost() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("content", "content goes here");
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + userToken);
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/activities");
    }

    /**
     * Testing the results from the response to make sure
     * the activity is created with the proper details.
     */
    @Then("The post is created")
    public void thePostIsCreated() {
        Map<String, String> activity = response.jsonPath().get("data");
        String msg = response.jsonPath().get("message");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");

        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals("content goes here", activity.get("content"));
        Assert.assertEquals(now.format(formatter), activity.get("activityDate"));
        Assert.assertEquals("success", msg);
    }

    /**
     * Setting up the request header and body to invoke a PUT endpoint to
     * update an activity content for the current logged-in user.
     *
     * @throws Exception
     */
    @When("I update any of my posts")
    public void iUpdateAnyOfMyPosts() throws Exception {
        JSONObject requestBody = new JSONObject();
        Map<String, String> activity = response.jsonPath().get("data");
        String activityId = String.valueOf(activity.get("id"));
        requestBody.put("content", "updated content");
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + userToken);
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/activities/" + activityId);
    }

    /**
     * Testing the results from the response to make sure
     * the activity is updated with the recent content.
     */
    @Then("The post is updated")
    public void thePostIsUpdated() {
        Map<String, String> activity = response.jsonPath().get("data");
        String msg = response.jsonPath().get("message");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");

        Assert.assertEquals(202, response.getStatusCode());
        Assert.assertEquals("updated content", activity.get("content"));
        Assert.assertEquals(now.format(formatter), activity.get("activityDate"));
        Assert.assertEquals("success", msg);
    }

    /**
     * Setting up the request header to invoke a GET endpoint to
     * get any existing activity for the current logged-in user.
     *
     * @throws Exception
     */
    @When("I search for a random post")
    public void iSearchForARandomPost() {
        request.header("Authorization", "Bearer " + userToken);
        response = request.get(BASE_URL + port + "/api/activities");
    }

    /**
     * Testing the results from the response to make sure
     * an activity exists.
     */
    @Then("The I receive a random post")
    public void theIReceiveARandomPost() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("success", response.jsonPath().get("message"));
    }
}

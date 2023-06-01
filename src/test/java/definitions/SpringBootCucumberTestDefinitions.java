package definitions;

import com.active.demo.ActiveApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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

    @Given("A list of my posts exists")
    public void aListOfMyPostsExists() {
        
    }

    @When("I create a new post")
    public void iCreateANewPost() {
        
    }

    @Then("The post is created")
    public void thePostIsCreated() {
        
    }

    @When("I update any of my posts")
    public void iUpdateAnyOfMyPosts() {
        
    }

    @Then("The post is updated")
    public void thePostIsUpdated() {
    }
}

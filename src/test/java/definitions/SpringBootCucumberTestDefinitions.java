package definitions;

import com.active.demo.ActiveApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ActiveApplication.class)
public class SpringBootCucumberTestDefinitions {
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

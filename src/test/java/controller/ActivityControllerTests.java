package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ActivityController.class)
public class ActivityControllerTests {
    @Autowired // dependency injection
    private MockMvc mockMvc; // Java Bean

    @Autowired
    ObjectMapper mapper; // serialization: maps object to model and vice versa

    @MockBean
    private ActivityService activityService;
}

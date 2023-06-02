package controller;

import com.active.demo.controller.UserController;
import com.active.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired // dependency injection
    private MockMvc mockMvc; // Java Bean

    @Autowired
    ObjectMapper mapper; // serialization: maps object to model and vice versa

    @MockBean
    private UserService userService;
}

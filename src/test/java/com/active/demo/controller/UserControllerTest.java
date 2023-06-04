package com.active.demo.controller;

import com.active.demo.model.User;
import com.active.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired // dependency injection
    private MockMvc mockMvc; // Java Bean

    @Autowired
    ObjectMapper mapper; // serialization: maps object to model and vice versa

    @MockBean
    private UserService userService;

    User USER_1 = new User(1L, "username1", "password1");
    User USER_2 = new User(2L, "username2", "password2");
    User USER_3 = new User(3L, "username3", "password3");

    @Test
    public void createUser_success() throws Exception {
        when(userService.createUser(Mockito.any(User.class))).thenReturn(USER_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/auth/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(USER_1)); // serialization: convert from JSON - String
        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.id").value(USER_1.getId()))
                .andExpect(jsonPath("$.data.userName").value(USER_1.getUserName()))
                .andExpect(jsonPath("$.message").value("success"))
                .andDo(print());
    }
}

package com.active.demo.controller;

import com.active.demo.model.Activity;
import com.active.demo.service.ActivityService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActivityController.class)
public class ActivityControllerTest {
    @Autowired // dependency injection
    private MockMvc mockMvc; // Java Bean

    @Autowired
    ObjectMapper mapper; // serialization: maps object to model and vice versa

    @MockBean
    private ActivityService activityService;

    DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
    LocalDateTime now = LocalDateTime.now();

    // Dummy data for test methods
    Activity RECORD_1 = new Activity(1L, now.format(form), "Content 1");
    Activity RECORD_2 = new Activity(2L, now.format(form), "Content 2");
    Activity RECORD_3 = new Activity(3L, now.format(form), "Content 3");

    @Test
    public void createActivity_success() throws Exception {
        when(activityService.createActivity(Mockito.any(Activity.class))).thenReturn(RECORD_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(RECORD_1)); // serialization: convert from JSON - String
        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.id").value(RECORD_1.getId()))
                .andExpect(jsonPath("$.data.activityDate").value(RECORD_1.getActivityDate()))
                .andExpect(jsonPath("$.data.content").value(RECORD_1.getContent()))
                .andExpect(jsonPath("$.message").value("success"))
                .andDo(print());
    }

    @Test
    public void createActivity_postTooEarly() throws Exception {
        when(activityService.createActivity(Mockito.any(Activity.class))).thenReturn(null);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest)
                .andExpect(status().isTooEarly())
                .andDo(print());
    }
}

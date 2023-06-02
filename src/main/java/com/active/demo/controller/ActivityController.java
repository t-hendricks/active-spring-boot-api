package com.active.demo.controller;

import com.active.demo.model.Activity;
import com.active.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    static HashMap<String, Object> message = new HashMap<>();

    @PostMapping("/activities")
    public ResponseEntity<?> createActivity(@RequestBody Activity activityObj) {
        Activity newActivity = activityService.createActivity(activityObj);
        if (newActivity != null) {
            message.put("message", "success");
            message.put("data", newActivity);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "unable to create an activity at this time");
            return new ResponseEntity<>(message, HttpStatus.TOO_EARLY);
        }
    }
}

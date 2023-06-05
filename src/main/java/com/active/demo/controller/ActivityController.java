package com.active.demo.controller;

import com.active.demo.model.Activity;
import com.active.demo.model.ActivityLike;
import com.active.demo.model.User;
import com.active.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/activities/{activityId}")
    public ResponseEntity<?> updateActivity(@PathVariable Long activityId, @RequestBody Activity activityObj) {
        Activity updatedActivity = activityService.updateActivity(activityId, activityObj);
        if (updatedActivity != null) {
            message.put("message", "success");
            message.put("data", updatedActivity);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } else {
            message.put("message", "cannot find activity with id " + activityId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/activities")
    public ResponseEntity<?> getRandomActivity() {
        Activity randActivity = activityService.getRandomActivity();
        if (randActivity != null) {
            message.put("message", "success");
            message.put("data", randActivity);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "no activities exists");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/activities/{activityId}")
    public ResponseEntity<?> addLikeToActivity(@PathVariable Long activityId) {
        ActivityLike activity = activityService.addLikeToActivity(activityId);
        if (activity != null) {
            message.put("message", "success");
            message.put("data", activity);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "activity with id: " + activityId + " don't exist");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/activities/{activityId}")
    public ResponseEntity<?> removeLikeFromActivity(@PathVariable Long activityId) {
        ActivityLike like = activityService.removeLikeFromActivity(activityId);
        if (like != null) {
            message.put("message", "success");
            message.put("data", like);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "activity with id: " + activityId + " don't exist");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/activities")
    public ResponseEntity<?> getCurrentUserContent() {
        User user = ActivityService.getCurrentLoggedInUser();
        message.put("message", "success");
        message.put("data", user);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

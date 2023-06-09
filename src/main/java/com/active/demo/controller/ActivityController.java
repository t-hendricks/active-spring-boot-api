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

    /**
     * Takes in an Activity object to make a POST request to create a new activity
     * with the given activityObj content.
     *
     * @param activityObj This is an {Activity} that holds activity content
     * @return ResponseEntity This returns message HashMap with a http status code
     */
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

    /**
     * Takes in an Activity object and activity id to make a PUT request to update
     * an existing activity with the given activityObj content.
     *
     * @param activityId This is a Long to represent activity id
     * @param activityObj This is an {Activity} that holds activity content
     * @return ResponseEntity This returns message HashMap with a http status code
     */
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

    /**
     * Makes a GET request to find an existing random activity.
     *
     * @return ResponseEntity This returns message HashMap with a http status code
     */
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

    /**
     * Takes in an activity id to make a POST request to find an existing activity
     * and add the activity to the current logged-in user's likes list.
     *
     * @param activityId This is a Long to represent activity id
     * @return ResponseEntity This returns message HashMap with a http status code
     */
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

    /**
     * Takes in an activity id to make a DELETE request to find an existing activity
     * and remove the activity from the current logged-in user's likes list.
     *
     * @param activityId This is a Long to represent activity id
     * @return ResponseEntity This returns message HashMap with a http status code
     */
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

    /**
     * Makes a GET request to get current logged-in user's content.
     *
     * @return ResponseEntity This returns message HashMap with a http status code
     */
    @GetMapping("/user/activities")
    public ResponseEntity<?> getCurrentUserContent() {
        User user = ActivityService.getCurrentLoggedInUser();
        message.put("message", "success");
        message.put("data", user);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

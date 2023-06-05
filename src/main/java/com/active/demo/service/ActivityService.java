package com.active.demo.service;

import com.active.demo.model.Activity;
import com.active.demo.model.User;
import com.active.demo.repository.ActivityRepository;
import com.active.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ActivityService {
    private ActivityRepository activityRepository;

    @Autowired
    public void setActivityRepository(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetails.getUser();
    }

    public Activity createActivity(Activity activityObj) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");

        activityObj.setUser(getCurrentLoggedInUser());
        activityObj.setActivityDate(now.format(formatter));

        return activityRepository.save(activityObj);
    }
}

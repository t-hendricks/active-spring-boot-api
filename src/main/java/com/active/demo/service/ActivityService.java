package com.active.demo.service;

import com.active.demo.model.Activity;
import com.active.demo.model.User;
import com.active.demo.repository.ActivityRepository;
import com.active.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
        activityObj.setUser(getCurrentLoggedInUser());
        return activityRepository.save(activityObj);
    }
}

package com.active.demo.service;

import com.active.demo.exception.InformationExistException;
import com.active.demo.exception.RepeatingException;
import com.active.demo.model.Activity;
import com.active.demo.model.User;
import com.active.demo.repository.ActivityRepository;
import com.active.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public boolean isPostTooEarly(LocalDateTime now) {
        List<Activity> userActivities = activityRepository.findByUserIdOrderByIdAsc(getCurrentLoggedInUser().getId());
        if (userActivities.size() == 0) return false;

        Activity latestPost = userActivities.get(userActivities.size() - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
        LocalDateTime latestDate = LocalDateTime.parse(latestPost.getActivityDate(), formatter);

        return latestDate.isBefore(now.minusMinutes(1)) ? false : true;
    }

    public Activity createActivity(Activity activityObj) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");

        if (!isPostTooEarly(now)) {
            activityObj.setUser(getCurrentLoggedInUser());
            activityObj.setActivityDate(now.format(formatter));
        } else {
            throw new RepeatingException("Cannot post less than 24 hours from your last post");
        }

        return activityRepository.save(activityObj);
    }
}

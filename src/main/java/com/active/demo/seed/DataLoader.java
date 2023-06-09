package com.active.demo.seed;

import com.active.demo.model.Activity;
import com.active.demo.model.User;
import com.active.demo.repository.ActivityLikeRepository;
import com.active.demo.repository.ActivityRepository;
import com.active.demo.repository.UserRepository;
import com.active.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ActivityRepository activityRepository;
    private final ActivityLikeRepository activityLikeRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, UserService userService, ActivityRepository activityRepository, ActivityLikeRepository activityLikeRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.activityRepository = activityRepository;
        this.activityLikeRepository = activityLikeRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        loadUserActivityData();
    }

    private void loadUserActivityData(){
        User user1 = new User(1L, "JonDoe", "password1");
        User user2 = new User(2L, "JaneDoe", "password2");
        User user3 = new User(3L, "Raffles", "password3");

        Activity activity1 = new Activity(1L, "06/08/2023 9:00 PM", "Go for a walk!");
        Activity activity2 = new Activity(2L, "05/08/2022 3:00 AM", "Read a book.");
        Activity activity3 = new Activity(3L, "04/11/2023 5:40 PM", "Sky dive.");
        Activity activity4 = new Activity(4L, "01/08/2023 4:05 PM", "Listen to a podcast.");
        Activity activity5 = new Activity(5L, "01/25/2023 1:30 PM", "Learn a new martial art.");
        Activity activity6 = new Activity(6L, "03/04/2023 2:27 PM", "Talk to a stranger.");
        Activity activity7 = new Activity(7L, "04/20/2023 6:13 AM", "Meditate...");

        activity1.setUser(user1);
        activity2.setUser(user2);
        activity3.setUser(user3);
        activity4.setUser(user1);
        activity5.setUser(user2);
        activity6.setUser(user3);
        activity7.setUser(user1);

        if(userRepository.count() == 0){
            userService.createUser(user1);
            userService.createUser(user2);
            userService.createUser(user3);
        }

        if(activityRepository.count() == 0){
            activityRepository.save(activity1);
            activityRepository.save(activity2);
            activityRepository.save(activity3);
            activityRepository.save(activity4);
            activityRepository.save(activity5);
            activityRepository.save(activity6);
            activityRepository.save(activity7);
        }
    }
}

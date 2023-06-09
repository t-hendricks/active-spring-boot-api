package com.active.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // One user can have many activities
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE) // loads activities immediately
    private List<Activity> activities;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ActivityLike> likedActivities;

    public User() {
    }

    public User(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<ActivityLike> getLikedActivities() {
        return likedActivities;
    }

    public void setLikedActivities(List<ActivityLike> likedActivities) {
        this.likedActivities = likedActivities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", activities=" + activities +
                ", likedActivities=" + likedActivities +
                '}';
    }
}

package com.active.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.SortedSet;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String activityDate;

    @Column(nullable = false)
    private String content;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "activity", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ActivityLike> likedActivities;

    public Activity() {
    }

    public Activity(Long id, String activityDate, String content) {
        this.id = id;
        this.activityDate = activityDate;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ActivityLike> getLikedActivities() {
        return likedActivities;
    }

    public void setLikedActivities(List<ActivityLike> likedActivities) {
        this.likedActivities = likedActivities;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityDate='" + activityDate + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", likedActivities=" + likedActivities +
                '}';
    }
}

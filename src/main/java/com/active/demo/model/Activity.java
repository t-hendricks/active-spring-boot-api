package com.active.demo.model;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityDate=" + activityDate +
                ", content='" + content + '\'' +
                '}';
    }
}

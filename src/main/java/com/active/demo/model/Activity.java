package com.active.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date activityDate;

    @Column
    private String content;

    public Activity() {
    }

    public Activity(Long id, Date activityDate, String content) {
        this.id = id;
        this.activityDate = activityDate;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
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

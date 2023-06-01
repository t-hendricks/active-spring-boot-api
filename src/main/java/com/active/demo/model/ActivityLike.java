package com.active.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "activity_likes")
public class ActivityLike {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ActivityLike() {
    }

    public ActivityLike(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ActivityLike{" +
                "id=" + id +
                '}';
    }
}

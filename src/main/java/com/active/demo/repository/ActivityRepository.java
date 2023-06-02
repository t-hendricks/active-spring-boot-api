package com.active.demo.repository;

import com.active.demo.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    public List<Activity> findByOrderByUserIdAsc(Long UserId);
}

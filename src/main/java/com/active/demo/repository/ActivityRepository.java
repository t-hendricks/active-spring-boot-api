package com.active.demo.repository;

import com.active.demo.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    public List<Activity> findByUserIdOrderByIdAsc(Long userId);
    public Activity findByIdAndUserId(Long id, Long userId);
    public Optional<Activity> findById(Long id);
}

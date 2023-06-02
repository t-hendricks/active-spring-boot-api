package com.active.demo.repository;

import com.active.demo.model.ActivityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLikeRepository extends JpaRepository<ActivityLike, Long> {
}

package com.yd.springboottrail.repository;

import com.yd.springboottrail.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    // delete by targetId and type
    void deleteByTargetIdAndType(Integer targetId, Integer type);
    // delete by usesr
    void deleteByUserId(Integer userId);
    // exists by targetId and userId and type
    boolean existsByTargetIdAndUserIdAndType(Integer targetId, Integer userId, Integer type);
} 
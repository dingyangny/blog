package com.yd.springboottrail.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yd.springboottrail.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    // find by name, if not found, return optional.empty
    Optional<Topic> findByName(String name);

}

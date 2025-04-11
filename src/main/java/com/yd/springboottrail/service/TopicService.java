package com.yd.springboottrail.service;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.Topic;
import com.yd.springboottrail.repository.TopicRepository;
import com.yd.springboottrail.vo.TopicCreateReqBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public ResponseEntity<Result<Topic>> createTopic(TopicCreateReqBody topic) {
        if (topic.getName() == null || topic.getName().isEmpty()) {
            return ResponseEntity.ok(new Result<>(-1, "topic name cannot be empty", null));
        }
        if (topic.getName().length() > 10 && topic.getName().length() < 3) {
            return ResponseEntity.ok(new Result<>(-1, "topic name cannot exceed 10 characters and less than 3 characters", null));
        }
        if (topic.getDescription() == null || topic.getDescription().isEmpty()) {
            return ResponseEntity.ok(new Result<>(-1, "topic description cannot be empty", null));
        }
        if (topic.getDescription().length() > 256) {
            return ResponseEntity.ok(new Result<>(-1, "topic description cannot exceed 256 characters", null));
        }
        if (topicRepository.findByName(topic.getName()).isPresent()) {
            return ResponseEntity.ok(new Result<>(-1, "topic name already exists", null));
        }
        Topic newTopic = topicRepository.save(new Topic(topic.getName(), topic.getDescription()));
        return ResponseEntity.ok(new Result<>(0, "create topic successfully", newTopic));
    }

    public ResponseEntity<Result<List<Topic>>> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return ResponseEntity.ok(new Result<>(0, "get topics successfully", topics));
    }

    public ResponseEntity<Result<Topic>> getTopicById(Integer id) {
        return topicRepository.findById(id)
                .map(topic -> ResponseEntity.ok(new Result<>(0, "get topic successfully", topic)))
                .orElse(ResponseEntity.ok(new Result<>(-1, "topic not found", null)));
    }


    public ResponseEntity<Result<Void>> deleteTopic(Integer id) {
        if (!topicRepository.existsById(id)) {
            return ResponseEntity.ok(new Result<>(-1, "topic not found", null));
        }

        topicRepository.deleteById(id);
        return ResponseEntity.ok(new Result<>(0, "delete topic successfully", null));
    }
}

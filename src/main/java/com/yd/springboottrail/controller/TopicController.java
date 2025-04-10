package com.yd.springboottrail.controller;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.Topic;
import com.yd.springboottrail.service.TopicService;
import com.yd.springboottrail.vo.TopicCreateReqBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Topic Controller")
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @ApiOperation("Create a new topic")
    @PostMapping
    public ResponseEntity<Result<Topic>> createTopic(
        @ApiParam(name = "topic", value = "The body of the topic creation request", required = true) @RequestBody TopicCreateReqBody topic) {
        return topicService.createTopic(topic);
    }

    @ApiOperation("Get all topics")
    @GetMapping
    public ResponseEntity<Result<List<Topic>>> getAllTopics() {
        return topicService.getAllTopics();
    }

    @ApiOperation("Get a topic by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Result<Topic>> getTopicById(
        @ApiParam(name = "id", value = "The ID of the topic", required = true) @PathVariable Integer id) {
        return topicService.getTopicById(id);
    }

    @ApiOperation("Delete a topic by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteTopic(
        @ApiParam(name = "id", value = "The ID of the topic to be deleted", required = true) @PathVariable Integer id) {
        return topicService.deleteTopic(id);
    }
}

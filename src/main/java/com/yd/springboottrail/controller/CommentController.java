package com.yd.springboottrail.controller;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.Comment;
import com.yd.springboottrail.service.CommentService;
import com.yd.springboottrail.vo.CommentCreateReqBody;
import com.yd.springboottrail.vo.CommentUpdateReqBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Comment Controller")
@RestController
@RequestMapping("blog/{blogId}/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("create a new comment")
    @PostMapping
    public ResponseEntity<Result<Comment>> createComment(@PathVariable Integer blogId, @RequestBody CommentCreateReqBody comment) {
        return commentService.createComment(blogId, comment);
    }

    @ApiOperation("get all comments comments of a blog")
    @GetMapping
    public ResponseEntity<Result<List<Comment>>> getAllComments(@PathVariable Integer blogId) {
        return commentService.getAllComments(blogId);
    }

    @ApiOperation("get a comment by id")
    @GetMapping("/{id}")
    public ResponseEntity<Result<Comment>> getCommentById(@PathVariable Integer blogId, @PathVariable Integer id) {
        return commentService.getCommentById(blogId, id);
    }

    @ApiOperation("update a comment")
    @PutMapping("/{id}")
    public ResponseEntity<Result<Comment>> updateComment(@PathVariable Integer blogId, @PathVariable Integer id, @RequestBody CommentUpdateReqBody comment) {
        return commentService.updateComment(blogId, id, comment);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteComment(@PathVariable Integer blogId, @PathVariable Integer id, @RequestParam(required = true) Integer userId) {
        return commentService.deleteComment(blogId, id, userId);
    }
}

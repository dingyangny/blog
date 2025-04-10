package com.yd.springboottrail.controller;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.Comment;
import com.yd.springboottrail.service.CommentService;
import com.yd.springboottrail.vo.CommentCreateReqBody;
import com.yd.springboottrail.vo.CommentUpdateReqBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public ResponseEntity<Result<Comment>> createComment(
        @ApiParam(name = "blogId", value = "the id of the blog who owns the comment", required = true) @PathVariable Integer blogId,
        @ApiParam(name = "comment", value = "the body of the comment creation request", required = true) @RequestBody CommentCreateReqBody comment) {
        return commentService.createComment(blogId, comment);
    }

    @ApiOperation("get all comments of a blog")
    @GetMapping
    public ResponseEntity<Result<List<Comment>>> getAllComments(
        @ApiParam(name = "blogId", value = "the id of the blog who owns the comments", required = true) @PathVariable Integer blogId) {
        return commentService.getAllComments(blogId);
    }

    @ApiOperation("get a comment by id")
    @GetMapping("/{id}")
    public ResponseEntity<Result<Comment>> getCommentById(
        @ApiParam(name = "blogId", value = "the id of the blog who owns the comment", required = true) @PathVariable Integer blogId,
        @ApiParam(name = "id", value = "the id of the comment", required = true) @PathVariable Integer id) {
        return commentService.getCommentById(blogId, id);
    }

    @ApiOperation("update a comment")
    @PutMapping("/{id}")
    public ResponseEntity<Result<Comment>> updateComment(
        @ApiParam(name = "blogId", value = "the id of the blog who owns the updating comment", required = true) @PathVariable Integer blogId,
        @ApiParam(name = "id", value = "the id of the comment to be updated", required = true) @PathVariable Integer id,
        @ApiParam(name = "comment", value = "the body of the comment update request", required = true) @RequestBody CommentUpdateReqBody comment) {
        return commentService.updateComment(blogId, id, comment);
    }

    @ApiOperation("delete a comment")
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteComment(
        @ApiParam(name = "blogId", value = "the id of the blog who owns the deleting comment", required = true) @PathVariable Integer blogId,
        @ApiParam(name = "id", value = "the id of the comment to be deleted", required = true) @PathVariable Integer id,
        @ApiParam(name = "userId", value = "the id of the user requesting the deletion", required = true) @RequestParam Integer userId) {
        return commentService.deleteComment(blogId, id, userId);
    }
}

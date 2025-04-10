package com.yd.springboottrail.service;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.Comment;
import com.yd.springboottrail.repository.BlogRepository;
import com.yd.springboottrail.repository.CommentRepository;
import com.yd.springboottrail.repository.LikeRepository;
import com.yd.springboottrail.repository.UserRepository;
import com.yd.springboottrail.vo.CommentCreateReqBody;
import com.yd.springboottrail.vo.CommentUpdateReqBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    public ResponseEntity<Result<Comment>> createComment(Integer blogId, CommentCreateReqBody comment) {
        if (!userRepository.existsById(comment.getUserId())) {
            return ResponseEntity.ok(new Result<>(-1, "user not found", null));
        }
        if (!blogRepository.existsById(blogId)) {
            return ResponseEntity.ok(new Result<>(-1, "blog not found", null));
        }
        if (comment.getContent() == null || comment.getContent().isEmpty()) {
            return ResponseEntity.ok(new Result<>(-1, "comment content cannot be empty", null));
        }
        if (comment.getContent().length() > 256) {
            return ResponseEntity.ok(new Result<>(-1, "comment content is too long", null));
        }


        Comment newComment = commentRepository.save(new Comment(blogId, comment.getUserId(), comment.getContent()));
        return ResponseEntity.ok(new Result<>(0, "create comment successfully", newComment));
    }

    public ResponseEntity<Result<List<Comment>>> getAllComments(Integer blogId) {
        if (!blogRepository.existsById(blogId)) {
            return ResponseEntity.ok(new Result<>(-1, "blog not found", null));
        }

        List<Comment> comments = commentRepository.findByBlogId(blogId);
        return ResponseEntity.ok(new Result<>(0, "get comments of a blog successfully", comments));
    }

    public ResponseEntity<Result<Comment>> getCommentById(Integer blogId, Integer id) {
        if (!blogRepository.existsById(blogId)) {
            return ResponseEntity.ok(new Result<>(-1, "blog not found", null));
        }

        if (!commentRepository.existsById(id)) {
            return ResponseEntity.ok(new Result<>(-1, "comment not found", null));
        }

        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null || !comment.getBlogId().equals(blogId)) {
            return ResponseEntity.ok(new Result<>(-1, "comment not found", null));
        }

        return ResponseEntity.ok(new Result<>(0, "get comment successfully", comment));
    }

    public ResponseEntity<Result<Comment>> updateComment(Integer blogId, Integer id, CommentUpdateReqBody comment) {
        if (!userRepository.existsById(comment.getUserId())) {
            return ResponseEntity.ok(new Result<>(-1, "user not found", null));
        }

        if (!blogRepository.existsById(blogId)) {
            return ResponseEntity.ok(new Result<>(-1, "blog not found", null));
        }

        if (!commentRepository.existsById(id)) {
            return ResponseEntity.ok(new Result<>(-1, "comment not found", null));
        }

        Comment existingComment = commentRepository.findById(id).orElse(null);
        if (existingComment == null || !existingComment.getBlogId().equals(blogId)) {
            return ResponseEntity.ok(new Result<>(-1, "comment not found", null));
        }

        if (!existingComment.getUserId().equals(comment.getUserId())) {
            return ResponseEntity.ok(new Result<>(-1, "no permission to modify this comment", null));
        }

        if (comment.getContent() == null || comment.getContent().isEmpty()) {
            return ResponseEntity.ok(new Result<>(-1, "comment content cannot be empty", null));
        }

        if (comment.getContent().length() > 256) {
            return ResponseEntity.ok(new Result<>(-1, "comment content is too long", null));
        }

        if (existingComment.getContent().equals(comment.getContent())) {
            return ResponseEntity.ok(new Result<>(-1, "no changes made to the content", null));
        }

        existingComment.setContent(comment.getContent());
        Comment updatedComment = commentRepository.save(existingComment);
        return ResponseEntity.ok(new Result<>(0, "update comment successfully", updatedComment));
    }

    @Transactional
    public ResponseEntity<Result<Void>> deleteComment(Integer blogId, Integer id, Integer userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.ok(new Result<>(-1, "user not found", null));
        }

        if (!blogRepository.existsById(blogId)) {
            return ResponseEntity.ok(new Result<>(-1, "blog not found", null));
        }

        if (!commentRepository.existsById(id)) {
            return ResponseEntity.ok(new Result<>(-1, "comment not found", null));
        }

        Comment existingComment = commentRepository.findById(id).orElse(null);
        if (existingComment == null || !existingComment.getBlogId().equals(blogId)) {
            return ResponseEntity.ok(new Result<>(-1, "comment not found", null));
        }

        if (!existingComment.getUserId().equals(userId)) {
            return ResponseEntity.ok(new Result<>(-1, "no permission to delete this comment", null));
        }

        commentRepository.deleteById(id);
        likeRepository.deleteByTargetIdAndType(id, 2);
        return ResponseEntity.ok(new Result<>(0, "delete comment successfully", null));
    }
}

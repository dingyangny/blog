package com.yd.springboottrail.service;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.Blog;
import com.yd.springboottrail.entity.Comment;
import com.yd.springboottrail.entity.Like;
import com.yd.springboottrail.entity.User;
import com.yd.springboottrail.repository.BlogRepository;
import com.yd.springboottrail.repository.CommentRepository;
import com.yd.springboottrail.repository.LikeRepository;
import com.yd.springboottrail.repository.UserRepository;
import com.yd.springboottrail.vo.UserUpdateReqBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    public ResponseEntity<Result<User>> createUser(String userName, String pwd) {
        // check if the username is empty
        if (userName == null || userName.isEmpty()) {
            return ResponseEntity.ok(new Result<>(-1, "username cannot be empty", null));
        }
        // check if the username already exists
        if (userRepository.findByUsername(userName).isPresent()) {
            return ResponseEntity.ok(new Result<>(-1, "username already exists", null));
        }
        // check if the password is empty
        if (pwd == null || pwd.isEmpty()) {
            return ResponseEntity.ok(new Result<>(-1, "password cannot be empty", null));
        }

        // create a new user and save it to the database
        User newUser = userRepository.save(new User(userName, pwd));
        return ResponseEntity.ok(new Result<>(0, "user created successfully", newUser));
    }

    public ResponseEntity<Result<List<User>>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(new Result<>(0, "users retrieved successfully", users));
    }

    public ResponseEntity<Result<User>> getUserById(Integer id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(new Result<>(0, "user retrieved successfully", user)))
                .orElse(ResponseEntity.ok(new Result<>(-1, "user not found", null)));
    }

    public ResponseEntity<Result<User>> updateUser(Integer id, UserUpdateReqBody userToBeUpdated) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.ok(new Result<>(-1, "user not found", null));
        }

        // get the user to be update
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.ok(new Result<>(-1, "user not found", null));
        }

        // update the username
        if (userToBeUpdated.getUsername() != null && !userToBeUpdated.getUsername().isEmpty()) {
            // check if the username already exists
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                return ResponseEntity.ok(new Result<>(-1, "username already exists", null));
            }
            user.setUsername(userToBeUpdated.getUsername());
        }

        // update the password
        if (userToBeUpdated.getPassword() != null && !userToBeUpdated.getPassword().isEmpty()) {
            user.setPassword(userToBeUpdated.getPassword());
        }

        // update user information
        user.setId(id);
        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(new Result<>(0, "user updated successfully", updatedUser));
    }

    @Transactional
    public ResponseEntity<Result<Void>> deleteUser(Integer id) {
        // check if user exists
        if (!userRepository.existsById(id)) {
            return ResponseEntity.ok(new Result<>(-1, "user not found", null));
        }
        // delete user
        userRepository.deleteById(id);
        // delete blogs associated with the user
        List<Blog> blogs = blogRepository.findByUserId(id);
        for (Blog blog : blogs) {
            blogService.deleteBlog(blog.getId(), id);
        }
        // delete comments associated with the user
        List<Comment> comments = commentRepository.findByUserId(id);
        for (Comment comment : comments) {
            commentService.deleteComment(comment.getBlogId(), comment.getId(), comment.getUserId());
        }
        // delete likes associated with the user
        likeRepository.deleteByUserId(id);
        // return success response
        return ResponseEntity.ok(new Result<>(0, "user deleted successfully", null));
    }

    @Transactional
    public ResponseEntity<Result<Void>> likeContent(Integer userId, String type, Integer targetId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.ok(new Result<>(-1, "user not found", null));
        }
        // check if the target content exists
        if (type.equals("blog") && !blogRepository.existsById(targetId)) {
            return ResponseEntity.ok(new Result<>(-1, "blog not found", null));
        } else if (type.equals("comment") && !commentRepository.existsById(targetId)) {
            return ResponseEntity.ok(new Result<>(-1, "comment not found", null));
        }
        // check if the user already liked the content
        if (likeRepository.existsByTargetIdAndUserIdAndType(targetId, userId, type.equals("blog") ? 1 : 2)) {
            return ResponseEntity.ok(new Result<>(-1, "already liked", null));
        }

        // create a new like and save it to the database
        Like like = new Like(targetId, userId, type.equals("blog") ? 1 : 2);
        likeRepository.save(like);

        // increment the like count for the target content
        if (type.equals("blog")) {
            Blog blog = blogRepository.findById(targetId).orElse(null);
            if (blog != null) {
                blog.setLikes(blog.getLikes() + 1);
                blogRepository.save(blog);
            }
        } else if (type.equals("comment")) {
            Comment comment = commentRepository.findById(targetId).orElse(null);
            if (comment != null) {
                comment.setLikes(comment.getLikes() + 1);
                commentRepository.save(comment);
            }
        }

        return ResponseEntity.ok(new Result<>(0, "liked successfully", null));
    }
}

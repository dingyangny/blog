package com.yd.springboottrail.service;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.Blog;
import com.yd.springboottrail.entity.Comment;
import com.yd.springboottrail.repository.BlogRepository;
import com.yd.springboottrail.repository.CommentRepository;
import com.yd.springboottrail.repository.LikeRepository;
import com.yd.springboottrail.repository.TopicRepository;
import com.yd.springboottrail.repository.UserRepository;
import com.yd.springboottrail.vo.BlogCreateReqBody;
import com.yd.springboottrail.vo.BlogUpdateReqBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CommentService commentService;

    public ResponseEntity<Result<Blog>> createBlog(BlogCreateReqBody requestBody) {
        if (!userRepository.existsById(requestBody.getUserId())) {
            return ResponseEntity.ok(new Result<>(-1, "user not found", null));
        }

        if (requestBody.getContent() == null || requestBody.getContent().isEmpty()) {
            return ResponseEntity.ok(new Result<>(-1, "blog content cannot be empty", null));
        }

        if (requestBody.getContent().length() > 512) {
            return ResponseEntity.ok(new Result<>(-1, "blog content cannot exceed 512 characters", null));
        }

        Blog newBlog = null;
        // get the topics related to the blog
        if (requestBody.getTopics() == null || requestBody.getTopics().isEmpty()) {
            newBlog = new Blog(requestBody.getContent(), requestBody.getUserId());
        } else {
            String topicsStr = requestBody.getTopics();
            String[] topics = topicsStr.split(",");

            List<String> topicsStringToAdd = new ArrayList<>();
            // check if the topics are valid
            for (String topic : topics) {
                if (topic == null || topic.isEmpty()) {
                    continue;
                }
                if (topic.length() > 10) {
                    continue;
                }
                // check if the topic exists
                if (topicRepository.findByName(topic).isPresent()) {
                    topicsStringToAdd.add(topic);
                }
            }

            if (topicsStringToAdd.isEmpty()) {
                newBlog = new Blog(requestBody.getContent(), requestBody.getUserId());
            } else{ 
                String topicsString = String.join(",", topicsStringToAdd);
                newBlog = new Blog(requestBody.getContent(), requestBody.getUserId(), topicsString);
            }
        }
        newBlog = blogRepository.save(newBlog);
        return ResponseEntity.ok(new Result<>(0, "create blog successfully", newBlog));
    }

    public ResponseEntity<Result<List<Blog>>> getAllBlogs(Integer userId) {
        List<Blog> blogs;
        if (userId != null) {
            if (!userRepository.existsById(userId)) {
                return ResponseEntity.ok(new Result<>(-1, "user not found", null));
            }
            blogs = blogRepository.findByUserId(userId);
        } else {
            blogs = blogRepository.findAll();
        }
        return ResponseEntity.ok(new Result<>(0, "get blogs successfully", blogs));
    }

    public ResponseEntity<Result<Blog>> getBlogById(Integer id) {
        return blogRepository.findById(id)
                .map(blog -> ResponseEntity.ok(new Result<>(0, "get blog successfully", blog)))
                .orElse(ResponseEntity.ok(new Result<>(-1, "blog not found", null)));
    }

    public ResponseEntity<Result<Blog>> updateBlog(Integer id, BlogUpdateReqBody updateReqBody) {
        if (!userRepository.existsById(updateReqBody.getUserId())) {
            return ResponseEntity.ok(new Result<>(-1, "user not found", null));
        }

        if (!blogRepository.existsById(id)) {
            return ResponseEntity.ok(new Result<>(-1, "blog not found", null));
        }

        Blog existingBlog = blogRepository.findById(id).orElse(null);
        if (existingBlog == null) {
            return ResponseEntity.ok(new Result<>(-1, "blog not found", null));
        }

        if (!existingBlog.getUserId().equals(updateReqBody.getUserId())) {
            return ResponseEntity.ok(new Result<>(-1, "no permission to modify this blog", null));
        }

        if (updateReqBody.getContent() == null || updateReqBody.getContent().isEmpty()) {
            return ResponseEntity.ok(new Result<>(-1, "blog content cannot be empty", null));
        }

        if (existingBlog.getContent().equals(updateReqBody.getContent())) {
            return ResponseEntity.ok(new Result<>(-1, "no changes made to the content", null));
        }

        if (updateReqBody.getContent().length() > 512) {
            return ResponseEntity.ok(new Result<>(-1, "blog content cannot exceed 512 characters", null));
        }

        if(updateReqBody.getTopics() != null && ! updateReqBody.getTopics().isEmpty()) {
            String topicsStr = updateReqBody.getTopics();
            String[] topics = topicsStr.split(",");

            List<String> topicsStringToAdd = new ArrayList<>();
            // check if the topics are valid
            for (String topic : topics) {
                if (topic == null || topic.isEmpty()) {
                    continue;
                }
                if (topic.length() > 10) {
                    continue;
                }
                // check if the topic exists
                if (topicRepository.findByName(topic).isPresent()) {
                    topicsStringToAdd.add(topic);
                }
            }

            if (!topicsStringToAdd.isEmpty()) {
                String topicsString = String.join(",", topicsStringToAdd);
                existingBlog.setTopics(topicsString);
            }
        }

        existingBlog.setContent(updateReqBody.getContent());
        Blog updatedBlog = blogRepository.save(existingBlog);
        return ResponseEntity.ok(new Result<>(0, "blog updated successfully", updatedBlog));
    }

    @Transactional
    public ResponseEntity<Result<Void>> deleteBlog(Integer id, Integer userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.ok(new Result<>(-1, "user not found", null));
        }

        if (!blogRepository.existsById(id)) {
            return ResponseEntity.ok(new Result<>(-1, "blog not found", null));
        }

        if (!blogRepository.findById(id).get().getUserId().equals(userId)) {
            return ResponseEntity.ok(new Result<>(-1, "no permission to delete this blog", null));
        }

        // delete the blog
        blogRepository.deleteById(id);
        // delete all comments related to this blog
        List<Comment> comments = commentRepository.findByBlogId(id);
        for (Comment comment : comments) {
            commentService.deleteComment(id, comment.getId(), comment.getUserId());
        }
        // delete all likes related to this blog
        likeRepository.deleteByTargetIdAndType(id, 1);
        return ResponseEntity.ok(new Result<>(0, "delete blog successfully", null));
    }
}

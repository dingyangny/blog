package com.yd.springboottrail.controller;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.Blog;
import com.yd.springboottrail.service.BlogService;
import com.yd.springboottrail.vo.BlogCreateReqBody;
import com.yd.springboottrail.vo.BlogUpdateReqBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Blog Controller")
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @ApiOperation("create a new blog")
    @PostMapping
    public ResponseEntity<Result<Blog>> createBlog(@RequestBody BlogCreateReqBody requestBody) {
        return blogService.createBlog(requestBody);
    }

    @ApiOperation("get all blogs or blogs of a user")
    @GetMapping
    public ResponseEntity<Result<List<Blog>>> getAllBlogs(@RequestParam(required = false) Integer userId) {
        return blogService.getAllBlogs(userId);
    }

    @ApiOperation("get a blog by id")
    @GetMapping("/{id}")
    public ResponseEntity<Result<Blog>> getBlogById(@PathVariable Integer id) {
        return blogService.getBlogById(id);
    }

    @ApiOperation("update a blog")
    @PutMapping("/{id}")
    public ResponseEntity<Result<Blog>> updateBlog(@PathVariable Integer id, @RequestBody BlogUpdateReqBody updateReqBody) {
        return blogService.updateBlog(id, updateReqBody);
    }

    @ApiOperation("delete a blog")
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteBlog(@PathVariable Integer id, @RequestParam(required = true) Integer userId) {
        return blogService.deleteBlog(id, userId);
    }
}

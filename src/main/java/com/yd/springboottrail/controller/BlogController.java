package com.yd.springboottrail.controller;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.Blog;
import com.yd.springboottrail.service.BlogService;
import com.yd.springboottrail.vo.BlogCreateReqBody;
import com.yd.springboottrail.vo.BlogUpdateReqBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public ResponseEntity<Result<Blog>> createBlog(
        @ApiParam(name = "blog", value = "the body of the blog creation request", required = true) @RequestBody BlogCreateReqBody blog) {
        return blogService.createBlog(blog);
    }

    @ApiOperation("get all blogs or blogs of a user")
    @GetMapping
    public ResponseEntity<Result<List<Blog>>> getAllBlogs(
        @ApiParam(name = "userId", value = "the id of the user whose blogs to retrieve, optional", required = false) @RequestParam(required = false) Integer userId) {
        return blogService.getAllBlogs(userId);
    }

    @ApiOperation("get a blog by id")
    @GetMapping("/{id}")
    public ResponseEntity<Result<Blog>> getBlogById(
        @ApiParam(name = "id", value = "the id of the blog to retrieve", required = true) @PathVariable Integer id) {
        return blogService.getBlogById(id);
    }

    @ApiOperation("update a blog")
    @PutMapping("/{id}")
    public ResponseEntity<Result<Blog>> updateBlog(
        @ApiParam(name = "id", value = "the id of the blog to update", required = true) @PathVariable Integer id,
        @ApiParam(name = "blog", value = "the body of the blog update request", required = true) @RequestBody BlogUpdateReqBody blog) {
        return blogService.updateBlog(id, blog);
    }

    @ApiOperation("delete a blog")
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteBlog(
        @ApiParam(name = "id", value = "the id of the blog to delete", required = true) @PathVariable Integer id,
        @ApiParam(name = "userId", value = "the id of the user requesting the deletion", required = true) @RequestParam(required = true) Integer userId) {
        return blogService.deleteBlog(id, userId);
    }
}

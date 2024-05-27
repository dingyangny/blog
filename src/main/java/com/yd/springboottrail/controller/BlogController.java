package com.yd.springboottrail.controller;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.Blog;
import com.yd.springboottrail.repository.BlogRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "Blog Controller")
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogRepository blogRepository;

    @ApiOperation("create a new blog")
    @PostMapping
    public Result<Blog> createOneBlog(@RequestParam String content){
        Blog blog = new Blog(content);
        Blog newBlog = blogRepository.save(blog);
        return new Result<>(0,"成功创建博客",newBlog);
    }

    @ApiOperation("get a blog from the given blogId")
    @GetMapping(params = "blogId")
    public Result<Blog> getOneBlog(@RequestParam Integer blogId){
        Blog blog = blogRepository.getOne(blogId);
        if(blog!=null){
            return new Result<>(0,"获取成功",blog);
        }
        else {
            return new Result<>(-1,"未找到对应博客",null);
        }
    }

    @ApiOperation("get all blogs")
    @GetMapping
    public Result<List<Blog>> getAllBlogs(){
        return new Result<>(0,"返回成功",blogRepository.findAll());
    }

    @ApiOperation("update one blog")
    @PutMapping
    public Result<Blog> updateOneBlog(@RequestParam Integer blogId,@RequestParam String content){
        List<Blog> blogs = blogRepository.findAll();
        for(Blog blog:blogs){
            if(blog.getId()==blogId){
                if(blog.getContent().equals(content)){
                    return new Result<>(1,"内容没有改变",blog);
                }
                Blog newBlog = blogRepository.save(new Blog(blogId,content));
                return new Result<>(0,"更新成功",newBlog);
            }
        }
        return new Result<>(-1,"更新失败，未找到对应博客",null);
    }

    @ApiOperation("delete a blog")
    @DeleteMapping
    public Result<Blog> deleteOneBlog(@RequestParam Integer blogId){
        List<Blog> blogs = blogRepository.findAll();
        for(Blog blog:blogs){
            if(blog.getId()==blogId){
                blogRepository.deleteById(blogId);
                return new Result<>(0,"删除成功",null);
            }
        }
        return new Result<>(-1,"删除失败，未找到对应博客",null);
    }
}

package com.yd.springboottrail.controller;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.User;
import com.yd.springboottrail.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User Controller")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("create a new user")
    @PostMapping
    public ResponseEntity<Result<User>> createUser(@RequestParam String userName, @RequestParam String pwd) {
        return userService.createUser(userName, pwd);
    }

    @ApiOperation("get all users")
    @GetMapping
    public ResponseEntity<Result<List<User>>> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation("get a user by id")
    @GetMapping("/{id}")
    public ResponseEntity<Result<User>> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @ApiOperation("update a user")
    @PutMapping("/{id}")
    public ResponseEntity<Result<User>> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @ApiOperation("delete a user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @ApiOperation("like a blog or a comment")
    @PostMapping("/{id}/like")
    public ResponseEntity<Result<Void>> likeContent(@PathVariable Integer id, @RequestParam String type, @RequestParam Integer targetId) {
        return userService.likeContent(id, type, targetId);
    }
}
package com.yd.springboottrail.controller;

import com.yd.springboottrail.Result;
import com.yd.springboottrail.entity.User;
import com.yd.springboottrail.service.UserService;
import com.yd.springboottrail.vo.UserUpdateReqBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("create a new user")
    @PostMapping
    public ResponseEntity<Result<User>> createUser(
        @ApiParam(name = "userName", value = "the user's name", required = true) @RequestParam String userName,
        @ApiParam(name = "pwd", value = "the user's password", required = true) @RequestParam String pwd) {
        return userService.createUser(userName, pwd);
    }

    @ApiOperation("get all the users")
    @GetMapping
    public ResponseEntity<Result<List<User>>> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation("get a user by id")
    @GetMapping("/{id}")
    public ResponseEntity<Result<User>> getUserById(
        @ApiParam(name="id", value = "the id of the user", required = true) @PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @ApiOperation("update a user")
    @PutMapping("/{id}")
    public ResponseEntity<Result<User>> updateUser(
        @ApiParam(name = "id", value = "the id of the user to be updating", required = true)@PathVariable Integer id, 
        @ApiParam(name = "user", value = "the body of the updaing user request", required = true) @RequestBody UserUpdateReqBody user) {
        return userService.updateUser(id, user);
    }

    @ApiOperation("delete a user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteUser(
        @ApiParam(name = "id", value = "the id of the user to be deleting", required = true) @PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @ApiOperation("like a blog or a comment")
    @PostMapping("/{id}/like")
    public ResponseEntity<Result<Void>> likeContent(
        @ApiParam(name = "id", value = "the id of the user who is liking a blog or a comment", required = true) @PathVariable Integer id,
        @ApiParam(name = "type", value = "the type of the target to be liked, it can be blog or comment", required = true) @RequestParam String type,
        @ApiParam(name = "targetId", value = "the id of the target to be liked", required = true) @RequestParam Integer targetId) {
        return userService.likeContent(id, type, targetId);
    }
}
package com.waa.lab2.controller;

import com.waa.lab2.domain.User;
import com.waa.lab2.dto.incoming.PostDto;
import com.waa.lab2.dto.incoming.UserDto;
import com.waa.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<UserDto> getAll(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public UserDto findById(@PathVariable long id){
        return userService.findById(id);
    }
    @PostMapping("/")
    public UserDto save(@RequestBody UserDto user){
        return userService.save(user);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable long id){
        userService.deleteById(id);
    }
    @GetMapping("/{id}/posts")
    public UserDto getPostsOfUser(@PathVariable long id){
        return userService.getPostsOfUser(id);
    }
    @PostMapping("/{id}/posts")
    public UserDto createPostOfUser(@PathVariable("id") long userId, @RequestBody PostDto postDto){
        return userService.createPostOfUser(userId,postDto);
    }
}

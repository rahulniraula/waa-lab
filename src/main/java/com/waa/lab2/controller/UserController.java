package com.waa.lab2.controller;

import com.waa.lab2.aspect.annotation.ExecutionTime;
import com.waa.lab2.domain.User;
import com.waa.lab2.dto.incoming.CommentDto;
import com.waa.lab2.dto.incoming.PostDto;
import com.waa.lab2.dto.incoming.UserDto;
import com.waa.lab2.service.CommentService;
import com.waa.lab2.service.UserDetails;
import com.waa.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public List<UserDto> getAll(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    @ExecutionTime
    public UserDto findById(@PathVariable long id) {
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
        var a=(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.createPostOfUser(a.getId(),postDto);
    }
    /**
     * Now Lab 3 begins
     */
    @PostMapping("/{userId}/posts/{postId}/comments")
    public CommentDto createComment(@PathVariable long userId, @PathVariable long postId,@RequestBody CommentDto commentDto){
        return userService.createComment(userId,postId,commentDto);
    }

    @GetMapping("/{userId}/posts/{postId}/comments/{commentId}")
    public CommentDto getComment(@PathVariable long commentId){
        return commentService.findById(commentId);
    }
    @GetMapping("/filter")
    public List<UserDto> filterUsers(
            @RequestParam(name="min-count",required = false) Integer minCount,
            @RequestParam(name ="post-title",required = false) String postTitle
    ){
        if(minCount!=null){
            return userService.filterUser(minCount);
        }else{
            return userService.findUsersWhosePostTitleContains(postTitle);
        }

    }
    @GetMapping("/test-exception")
    public void testException() throws Exception {
        throw new Exception("New Exception Thrown");
    }
}

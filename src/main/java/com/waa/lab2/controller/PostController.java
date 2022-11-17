package com.waa.lab2.controller;

import com.waa.lab2.dto.incoming.PostDto;
import com.waa.lab2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public List<PostDto> findAll(){
        return postService.findAll();
    }
    @GetMapping("/search")
    public List<PostDto> search(@RequestParam(required = false) String title){
        return postService.search(title);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }
    @PostMapping("/")
    public PostDto save(@RequestBody PostDto postDto){
        return postService.save(postDto);
    }
    @GetMapping("/{id}")
    public PostDto findById(@PathVariable long id){
        return postService.findById(id);
    }
}

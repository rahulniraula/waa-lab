package com.waa.lab2.controller;

import com.waa.lab2.dto.incoming.PostDto;
import com.waa.lab2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @GetMapping("/search")
    public List<PostDto> search(@RequestParam(required = false) String title){
        return postService.search(title);
    }
}

package com.waa.lab2.service;

import com.waa.lab2.dto.incoming.PostDto;

import java.util.List;

public interface PostService {
    public List<PostDto> search(String title);

    List<PostDto> findAll();

    void deletePost(Long id);

    PostDto save(PostDto postDto);

    PostDto findById(long id);
}

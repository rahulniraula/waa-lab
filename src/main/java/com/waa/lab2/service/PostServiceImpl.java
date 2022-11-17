package com.waa.lab2.service;

import com.waa.lab2.domain.Post;
import com.waa.lab2.dto.incoming.PostDto;
import com.waa.lab2.repo.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<PostDto> search(String title) {
        return postRepo.findByTitleContaining(title).stream().map(p->modelMapper.map(p,PostDto.class)).toList();
    }

    @Override
    public List<PostDto> findAll() {
        List<PostDto> list=new ArrayList<>();
        postRepo.findAll().forEach(post -> list.add(modelMapper.map(post,PostDto.class)));
        return list;
    }

    @Override
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public PostDto save(PostDto postDto) {
        return modelMapper.map(postRepo.save(modelMapper.map(postDto, Post.class)),PostDto.class);
    }

    @Override
    public PostDto findById(long id) {
        return modelMapper.map(postRepo.findById(id), PostDto.class);
    }
}

package com.waa.lab2.service;

import com.waa.lab2.dto.incoming.PostDto;
import com.waa.lab2.repo.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

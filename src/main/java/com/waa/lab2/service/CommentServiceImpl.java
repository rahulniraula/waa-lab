package com.waa.lab2.service;

import com.waa.lab2.dto.incoming.CommentDto;
import com.waa.lab2.repo.CommentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto findById(long id) {
        return modelMapper.map(commentRepo.findById(id),CommentDto.class);
    }
}

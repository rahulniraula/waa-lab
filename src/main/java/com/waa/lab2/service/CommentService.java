package com.waa.lab2.service;

import com.waa.lab2.dto.incoming.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto findById(long id);
}

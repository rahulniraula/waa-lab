package com.waa.lab2.service;


import com.waa.lab2.domain.User;
import com.waa.lab2.dto.incoming.PostDto;
import com.waa.lab2.dto.incoming.UserDto;

import java.util.List;

public interface UserService {
    public UserDto findById(long id);
    List<UserDto> findAll();
    UserDto save(UserDto user);
    void deleteById(Long id);
    UserDto getPostsOfUser(long id);
    public UserDto createPostOfUser(long userId, PostDto postDto);
}

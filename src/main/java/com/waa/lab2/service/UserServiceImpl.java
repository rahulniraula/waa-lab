package com.waa.lab2.service;

import com.waa.lab2.domain.Comment;
import com.waa.lab2.domain.Post;
import com.waa.lab2.domain.User;
import com.waa.lab2.dto.incoming.CommentDto;
import com.waa.lab2.dto.incoming.PostDto;
import com.waa.lab2.dto.incoming.UserDto;
import com.waa.lab2.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public UserDto findById(long id) {
        return modelMapper.map(userRepo.findById(id).orElse(null),UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepo.findAll().stream().map(u->modelMapper.map(u,UserDto.class)).toList();
    }
    public UserDto save(UserDto userDto){
        var user=userRepo.save(modelMapper.map(userDto,User.class));
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDto getPostsOfUser(long id) {
        var user=userRepo.findById(id);
        return modelMapper.map(user.get(),UserDto.class);
    }
    public UserDto createPostOfUser(long userId, PostDto postDto){
        var user=userRepo.findById(userId).orElseThrow();
        user.getPosts().add(modelMapper.map(postDto, Post.class));
        var u=userRepo.save(user);
        return modelMapper.map(u, UserDto.class);
    }
    @Transactional
    public CommentDto createComment(long userId, long postId, CommentDto commentDto){
        Post post=entityManager.find(Post.class,postId);
        post.getComments().add(modelMapper.map(commentDto, Comment.class));
        entityManager.persist(post);
        return commentDto;
    }
    @Override
    public List<UserDto> filterUser(int postCount){
        List<User> userByPostCount = userRepo.findUserByPostCount(postCount);
        return userByPostCount.stream().map(u->modelMapper.map(u,UserDto.class)).toList();
    }
    public List<UserDto> findUsersWhosePostTitleContains(String postTitle){
        List<User> userByPostCount = userRepo.findUsersWhosePostTitleContains(postTitle);
        return userByPostCount.stream().map(u->modelMapper.map(u,UserDto.class)).toList();
    }
}

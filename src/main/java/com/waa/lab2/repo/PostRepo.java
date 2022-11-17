package com.waa.lab2.repo;

import com.waa.lab2.domain.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends CrudRepository<Post,Long> {
    public List<Post> findByTitleContaining(String title);
    public Post findById(long id);
}

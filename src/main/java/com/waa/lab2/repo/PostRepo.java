package com.waa.lab2.repo;

import com.waa.lab2.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post,Long> {
}

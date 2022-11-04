package com.waa.lab2.repo;

import com.waa.lab2.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment,Long> {

    public Comment findById(long id);
}

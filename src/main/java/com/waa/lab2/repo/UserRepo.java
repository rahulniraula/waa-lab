package com.waa.lab2.repo;

import com.waa.lab2.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User,Long> {
    List<User> findAll();
}

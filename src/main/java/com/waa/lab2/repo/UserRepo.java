package com.waa.lab2.repo;

import com.waa.lab2.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends CrudRepository<User,Long> {
    List<User> findAll();
    @Query("SELECT u from User u where u.posts.size>= :postCount")
    List<User> findUserByPostCount(int postCount);

    @Query(value = "select * from users join post on users.id = post.user_id where post.title LIKE %:postTitle%",nativeQuery = true)
    List<User> findUsersWhosePostTitleContains(@Param("postTitle") String postTitle);
}

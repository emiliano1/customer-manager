package com.test.project.repository;

import com.test.project.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByUsernameEqualsOrEmailEquals(String username, String email);
}

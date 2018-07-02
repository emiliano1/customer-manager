package com.test.project.service;

import com.test.project.entity.User;
import com.test.project.repository.BaseRepository;
import com.test.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends BaseService<User, Long> implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameEqualsOrEmailEquals(username, username).orElse(null);
    }

    public UserDetails loadUserByUserId(Long id) throws UsernameNotFoundException {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public BaseRepository<User, Long> getRepository() {
        return userRepository;
    }
}

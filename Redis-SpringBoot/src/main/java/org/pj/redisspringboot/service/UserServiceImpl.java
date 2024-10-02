package org.pj.redisspringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.pj.redisspringboot.constant.EnumErrorCode;
import org.pj.redisspringboot.exception.BusinessException;
import org.pj.redisspringboot.model.request.User;
import org.pj.redisspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements IUserSerivce {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        log.info("Begin: Saving user");
        User savedUser = userRepository.save(user);
        log.info("End: Saved user with ID: {}", user.getId());
        return savedUser;
    }

    @Cacheable(value = "users", key = "#id")
    public User findById(String id) {
        log.info("Begin: Fetching user with ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.warn("User not found with ID: {}", id);
            throw new BusinessException(EnumErrorCode.USER_NOT_FOUND);
        }
        log.info("End: Found user with ID: {}", id);
        return user.get();
    }

    public Iterable<User> findAll() {
        log.info("Begin: Fetching all users");
        Iterable<User> users = userRepository.findAll();
        log.info("End: Fetched {} users", ((Collection<?>) users).size());
        return users;
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteById(String id) {
        log.info("Begin: Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        log.info("End: Deleted user with ID: {}", id);
    }
}

package org.pj.redisspringboot.Service;

import org.pj.redisspringboot.Enum.ErrorCode;
import org.pj.redisspringboot.Exception.BusinessException;
import org.pj.redisspringboot.Model.Request.User;
import org.pj.redisspringboot.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        logger.info("Begin: Saving user");
        User savedUser = userRepository.save(user);
        logger.info("End: Saved user with ID: {}", user.getId());
        return savedUser;
    }

    @Cacheable(value = "users", key = "#id")
    public User findById(String id) {
        logger.info("Begin: Fetching user with ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            logger.warn("User not found with ID: {}", id);
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
        logger.info("End: Found user with ID: {}", id);
        return user.get();
    }

    public Iterable<User> findAll() {
        logger.info("Begin: Fetching all users");
        Iterable<User> users = userRepository.findAll();
        logger.info("End: Fetched {} users", ((Collection<?>) users).size());
        return users;
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteById(String id) {
        logger.info("Begin: Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        logger.info("End: Deleted user with ID: {}", id);
    }
}

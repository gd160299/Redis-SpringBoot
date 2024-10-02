package org.pj.redisspringboot.service;

import org.pj.redisspringboot.model.request.User;

public interface IUserSerivce {
    User save(User user);
    User findById(String id);
    Iterable<User> findAll();
    void deleteById(String id);
}

package com.project.service;

import com.project.exception.UserException;
import com.project.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User getUserById(Long id) throws UserException;
    List<User> getAllUser();
    void deleteUser(Long id) throws UserException;
    User updateUser(Long id, User user) throws UserException;
}

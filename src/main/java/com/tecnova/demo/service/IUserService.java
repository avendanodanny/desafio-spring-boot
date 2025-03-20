package com.tecnova.demo.service;

import com.tecnova.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User createUpdateUser(User user);

    public void deleteUser(Long id);

    public Optional<User> getUserByIdAndPasswd(Long id, String passwd);

}

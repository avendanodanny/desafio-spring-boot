package com.tecnova.demo.service;

import com.tecnova.demo.model.User;
import com.tecnova.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    /**
     * Method to get all the users
     * @return list of users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Method to get one user for id
     * @return user
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Method to create new user or update existing
     * @return create or update user
     */
    public User createUpdateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Method to delete one user for id
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Method to get one user for id and passwd
     * @param id user
     * @param passwd user
     * @return user or null
     */
    public Optional<User> getUserByIdAndPasswd(Long id, String passwd){
        return userRepository.findByIdAndPasswd(id, passwd);
    }
}

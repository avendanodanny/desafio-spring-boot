package com.tecnova.demo.repository;

import com.tecnova.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndPasswd(Long id, String passwd);

}

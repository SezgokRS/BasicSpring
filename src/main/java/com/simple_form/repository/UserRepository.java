package com.simple_form.repository;

import com.simple_form.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByLoginAndPassword(String login, String password);
    Optional<UserModel> findByLogin(String login);
    public UserModel findByEmail(String email);
    public UserModel findFirstByLogin(String login);
}

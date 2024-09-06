package com.simple_form.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String login;
    String email;
    String password;
    @OneToOne(mappedBy = "userModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PasswordResetToken passwordResetToken;

    public Integer getId(){
        return id;
    }
    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PasswordResetToken getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(login, userModel.login) && Objects.equals(email, userModel.email) && Objects.equals(password, userModel.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, password);
    }
}

package com.simple_form.service;

import com.simple_form.model.PasswordResetToken;
import com.simple_form.model.UserModel;
import com.simple_form.repository.TokenRepository;
import com.simple_form.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private JavaMailSender javaMailSender;
    private TokenRepository tokenRepository;

    public UserService(UserRepository userRepository, JavaMailSender javaMailSender, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
        this.tokenRepository = tokenRepository;
    }

    public UserModel registerUser(String login, String password, String email){
        if(login != null && password != null){
            if(userRepository.findFirstByLogin(login) != null){
                return null;
            }
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setEmail(email);
            userModel.setPassword(password);
            return userRepository.save(userModel);
        }
        else{
            return null;
        }
    }
    public UserModel authenticate(String login, String password){
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }
    public String sendEmail(UserModel userModel){
        try {
            String token = generateResetToken(userModel);

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom("imsezgoku@gmail.com");
            mail.setTo(userModel.getEmail());

            mail.setSubject("Password Reset");
            mail.setSubject("Link to reset your password: " + token);

            javaMailSender.send(mail);
            return "success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
    private String generateResetToken(UserModel userModel){
        UUID uuid = UUID.randomUUID();
        LocalDateTime expiryDateTime = LocalDateTime.now().plusMinutes(30);
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUserModel(userModel);
        passwordResetToken.setToken(uuid.toString());
        passwordResetToken.setExpiryDate(expiryDateTime);
        passwordResetToken.setUserModel(userModel);

        PasswordResetToken token = tokenRepository.save(passwordResetToken);

        if(token != null){
            return "localhost:8080/reset-password/" + passwordResetToken.getToken();
        }
        return "";
    }
}

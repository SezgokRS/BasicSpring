package com.simple_form.controller;

import com.simple_form.model.PasswordResetToken;
import com.simple_form.model.UserModel;
import com.simple_form.repository.TokenRepository;
import com.simple_form.repository.UserRepository;
import com.simple_form.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    String token;
    private UserService userService;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;

    public UserController(UserService userService, UserRepository userRepository, TokenRepository tokenRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UserModel());
        return "register_page";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }
    @GetMapping("forgot-password")
    public String getPasswordChange(Model model, String changing_email){
        model.addAttribute("changeRequest", new UserModel());
        return "forgot_password";
    }

    @GetMapping("reset-password/{token}")
    public String getResetPassword(String token, Model model){
        PasswordResetToken reset = tokenRepository.findByToken(token);
        model.addAttribute("email", reset.getUserModel());
        return "reset-password";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel){
        System.out.println("register request: " + userModel);
        UserModel registeredUserModel = userService.registerUser(userModel.getLogin(), userModel.getPassword(), userModel.getEmail());
        return registeredUserModel == null ? "error_page" : "redirect:/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel){
        System.out.println("login request: " + userModel);
        UserModel authenticated = userService.authenticate(userModel.getLogin(), userModel.getPassword());
        if (authenticated != null){
            return "account_page";
        }
        else{
            return "error_page";
        }
    }
    @PostMapping("/forgot-password")
    public String changePassword(@ModelAttribute UserModel userModel){
        String output = "";
        UserModel my_user = userRepository.findByEmail(userModel.getEmail());
        if(my_user != null){
            output = userService.sendEmail(my_user);
        }
        if(output.equals("sucess")){
            return "register_page";
        }
        return "error_page";
    }
    @PostMapping("/reset-password/{token}")
    public String passwordReset(@ModelAttribute UserModel userModel){
        UserModel my_user = userRepository.findByEmail(userModel.getEmail());
        if(my_user != null){
            my_user.setPassword(userModel.getPassword());
            userRepository.save(my_user);
        }
        return "login_page";
    }
}

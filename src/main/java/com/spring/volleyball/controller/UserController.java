package com.spring.volleyball.controller;


import com.spring.volleyball.model.User;
import com.spring.volleyball.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    @Qualifier("UserService")
    public UserService userService;

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword",
                    "error.user", "Slaptažodžiai nesutampa");
            return "signup";
        }

        if (userService.getUserByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email",
                    "error.user", "Toks el.pastas jau egzistuoja");
            return "signup";
        }

        userService.saveUser(user);
        return "redirect:/calculator";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        User existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser == null) {
            // Email does not exist, display error message
            bindingResult.rejectValue("email",
                    "error.user", "wrong email / password combo");
            return "login";
        } else if (!existingUser.getPassword().equals(user.getPassword())) {
            // Password does not match, display error message
            bindingResult.rejectValue("password",
                    "error.user", "wrong email / password combo");
            return "login";
        } else {
            // Authentication successful, redirect to home page
            return "redirect:/";
        }
    }
}
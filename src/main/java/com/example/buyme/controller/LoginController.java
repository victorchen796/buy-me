package com.example.buyme.controller;

import com.example.buyme.model.User;
import com.example.buyme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return new ModelAndView("redirect:/user/" + user.getUsername());
        }
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("error", session.getAttribute("error"));
        session.removeAttribute("error");
        return modelAndView;
    }

    @PostMapping("/login/attempt-login")
    public String loginAttempt(HttpSession session, String username, String password) {
        Optional<User> optionalUser = userService.findUserById(username);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                return "redirect:/home";
            }
        }
        session.setAttribute("error", "Username or password is incorrect");
        return "redirect:/login";
    }

    @PostMapping("/login/attempt-sign-up")
    public String signUpAttempt(HttpSession session, String username, String password) {
        Optional<User> optionalUser = userService.findUserById(username);
        if (optionalUser.isPresent()) {
            session.setAttribute("error", "User with that username already exists");
        } else if (username.matches(".*\\s+.*")) {
            session.setAttribute("error", "Username cannot contain any whitespace characters");
        } else if (password.length() < 8) {
            session.setAttribute("error", "Password must be at least 8 characters");
        } else {
            User user = new User(username, password, "regular", new Date(System.currentTimeMillis()));
            userService.saveUser(user);
            session.setAttribute("user", user);
            return "redirect:/home";
        }
        return "redirect:/login";
    }

    @GetMapping("/login/sign-out")
    public String signOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/home";
    }

}

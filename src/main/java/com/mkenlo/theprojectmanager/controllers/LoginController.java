package com.mkenlo.theprojectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mkenlo.theprojectmanager.models.LoginUser;
import com.mkenlo.theprojectmanager.models.User;
import com.mkenlo.theprojectmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("loginUser", new LoginUser());
        return "index.jsp";
    }

    @PostMapping("/register")
    public String createUser(@Valid @ModelAttribute("newUser") User user, BindingResult result, Model model) {
        userService.createUser(user, result, false);
        if (result.hasErrors()) {
            model.addAttribute("loginUser", new LoginUser());
            return "register.jsp";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null)
            model.addAttribute("error", "Username or Password incorrect");

        model.addAttribute("loginUser", new LoginUser());
        return "login.jsp";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("newUser", new User());
        return "register.jsp";
    }

}

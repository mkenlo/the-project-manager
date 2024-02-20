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
    public String createUser(@Valid @ModelAttribute("newUser") User user, BindingResult result, Model model,
            HttpSession session) {
        userService.createUser(user, result);
        if (result.hasErrors()) {
            model.addAttribute("loginUser", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("loggedUserId", user.getId());
        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String doLogin(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model,
            HttpSession session) {
        User user = userService.doLogin(loginUser, result);
        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("loggedUserId", user.getId());
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}

package com.mkenlo.theprojectmanager.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mkenlo.theprojectmanager.models.User;
import com.mkenlo.theprojectmanager.services.TaskService;
import com.mkenlo.theprojectmanager.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TaskController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public String index(Model model, Principal principal) {        
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("loggedUser", user);
        model.addAttribute("tasks", taskService.getAll());
        return "task-list.jsp";
    }

}

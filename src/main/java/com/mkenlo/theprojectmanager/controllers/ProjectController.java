package com.mkenlo.theprojectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mkenlo.theprojectmanager.models.Project;
import com.mkenlo.theprojectmanager.models.User;
import com.mkenlo.theprojectmanager.services.ProjectService;
import com.mkenlo.theprojectmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @GetMapping("/dashboard")
    public String index(Model model, HttpSession session, RedirectAttributes redirect) {
        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null) {
            redirect.addFlashAttribute("error", "Action requires to log in");
            return "redirect:/";
        }
        User user = userService.findById(userId);
        if (user == null) {
            redirect.addFlashAttribute("error", "Unknown User");
            return "redirect:/";
        }
        model.addAttribute("loggedUser", user);
        model.addAttribute("projects", projectService.getByExcludingUser(userId));
        return "dashboard.jsp";
    }

    @GetMapping("/projects/new")
    public String initEditForm(Model model, HttpSession session, RedirectAttributes redirect) {
        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null) {
            redirect.addFlashAttribute("error", "Action requires to log in");
            return "redirect:/";
        }
        User user = userService.findById(userId);
        if (user == null) {
            redirect.addFlashAttribute("error", "Unknown User");
            return "redirect:/";
        }
        model.addAttribute("loggedUser", user);
        model.addAttribute("newProject", new Project());
        return "project-new.jsp";
    }

    @PostMapping("/projects/new")
    public String processCreateProject(@Valid @ModelAttribute("newProject") Project project, BindingResult result,
            HttpSession session, RedirectAttributes redirect) {
        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null) {
            redirect.addFlashAttribute("error", "Action requires to log in");
            return "redirect:/";
        }
        User user = userService.findById(userId);
        if (user == null) {
            redirect.addFlashAttribute("error", "Unknown User");
            return "redirect:/";
        }

        if (result.hasErrors()) {
            return "project-new.jsp";
        }

        project.getAssignedUsers().add(user);
        projectService.save(project);

        return "redirect:/dashboard";
    }

}

package com.mkenlo.theprojectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mkenlo.theprojectmanager.models.Project;
import com.mkenlo.theprojectmanager.services.ProjectService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/dashboard")
    public String index(Model model, HttpSession session) {
        model.addAttribute("projects", projectService.getAll());
        return "dashboard.jsp";
    }

    @GetMapping("/projects/new")
    public String initEditForm(Model model) {
        model.addAttribute("newProject", new Project());
        return "project-new.jsp";
    }

}

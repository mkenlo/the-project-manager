package com.mkenlo.theprojectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public String initCreateForm(Model model, HttpSession session, RedirectAttributes redirect) {
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

        if (result.hasErrors()) {
            return "project-new.jsp";
        }

        project.getAssignedUsers().add(project.getLeader());
        projectService.save(project);

        return "redirect:/dashboard";
    }

    @GetMapping("/projects/{projectId}/join/{userId}")
    public String joinProjectTeam(@PathVariable("projectId") long projectId, @PathVariable("userId") long userId,
            HttpSession session, Model model, RedirectAttributes redirect) {
        Long loggedUserId = (Long) session.getAttribute("loggedUserId");

        if (loggedUserId == null) {
            redirect.addFlashAttribute("error", "Action requires to log in");
            return "redirect:/";
        }
        User user = userService.findById(userId);
        Project project = projectService.getById(projectId);
        if (user == null || project == null) {
            redirect.addFlashAttribute("error", "Invalid Payload");
            return "redirect:/dashboard";
        }

        project.getAssignedUsers().add(user);
        projectService.save(project);

        return "redirect:/dashboard";
    }

    @GetMapping("/projects/{projectId}/leave/{userId}")
    public String leaveProjectTeam(@PathVariable("projectId") long projectId, @PathVariable("userId") long userId,
            HttpSession session, Model model, RedirectAttributes redirect) {
        Long loggedUserId = (Long) session.getAttribute("loggedUserId");

        if (loggedUserId == null) {
            redirect.addFlashAttribute("error", "Action requires to log in");
            return "redirect:/";
        }
        User user = userService.findById(userId);
        Project project = projectService.getById(projectId);
        if (user == null || project == null) {
            redirect.addFlashAttribute("error", "Invalid Payload");
            return "redirect:/dashboard";
        }

        project.getAssignedUsers().remove(user);
        projectService.save(project);

        return "redirect:/dashboard";
    }

    @GetMapping("/projects/{projectId}")
    public String detailProject(@PathVariable("projectId") long projectId, HttpSession session, Model model,
            RedirectAttributes redirect) {
        Long loggedUserId = (Long) session.getAttribute("loggedUserId");

        if (loggedUserId == null) {
            redirect.addFlashAttribute("error", "Action requires to log in");
            return "redirect:/";
        }
        Project project = projectService.getById(projectId);
        if (project == null) {
            redirect.addFlashAttribute("error", "Invalid Payload");
            return "redirect:/dashboard";
        }

        model.addAttribute("project", project);
        model.addAttribute("loggedUser", userService.findById(loggedUserId));
        return "project-detail.jsp";
    }

    @GetMapping("/projects/{projectId}/edit")
    public String initEditForm(@PathVariable("projectId") long projectId, Model model, HttpSession session,
            RedirectAttributes redirect) {
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
        model.addAttribute("project", projectService.getById(projectId));
        return "project-edit.jsp";
    }

    @PutMapping("/projects/{projectId}/edit")
    public String processEditProject(@PathVariable("projectId") long projectId,
            @Valid @ModelAttribute("project") Project project, BindingResult result,
            HttpSession session, RedirectAttributes redirect) {
        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null) {
            redirect.addFlashAttribute("error", "Action requires to log in");
            return "redirect:/";
        }

        if (result.hasErrors()) {
            return "project-edit.jsp";
        }
        project.getAssignedUsers().add(project.getLeader());
        projectService.save(project);

        return "redirect:/dashboard";
    }

    @DeleteMapping("/projects/{projectId}/delete")
    public String deleteProject(@PathVariable("projectId") long projectId, HttpSession session,
            RedirectAttributes redirect) {

        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null) {
            redirect.addFlashAttribute("error", "Action requires to log in");
            return "redirect:/";
        }
        projectService.delete(projectId);
        return "redirect:/dashboard";

    }
}

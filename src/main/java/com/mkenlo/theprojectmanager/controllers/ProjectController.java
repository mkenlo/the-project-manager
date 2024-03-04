package com.mkenlo.theprojectmanager.controllers;

import java.security.Principal;

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
import com.mkenlo.theprojectmanager.models.Task;
import com.mkenlo.theprojectmanager.models.User;
import com.mkenlo.theprojectmanager.services.ProjectService;
import com.mkenlo.theprojectmanager.services.TaskService;
import com.mkenlo.theprojectmanager.services.UserService;
import jakarta.validation.Valid;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @GetMapping("/dashboard")
    public String index(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("loggedUser", user);
        model.addAttribute("projects", projectService.getByExcludingUser(user.getId()));
        return "dashboard.jsp";
    }

    @GetMapping("/projects/new")
    public String initCreateForm(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("loggedUser", userService.findByUsername(username));
        model.addAttribute("newProject", new Project());
        return "project-new.jsp";
    }

    @PostMapping("/projects/new")
    public String processCreateProject(@Valid @ModelAttribute("newProject") Project project, BindingResult result) {
        if (result.hasErrors()) {
            return "project-new.jsp";
        }

        project.getAssignedUsers().add(project.getLeader());
        projectService.save(project);

        return "redirect:/dashboard";
    }

    @GetMapping("/projects/{projectId}/join/{userId}")
    public String joinProjectTeam(@PathVariable("projectId") long projectId, @PathVariable("userId") long userId,
            Principal principal, RedirectAttributes redirect) {
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
            Model model, RedirectAttributes redirect) {

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
    public String detailProject(@PathVariable("projectId") long projectId, Model model,
            RedirectAttributes redirect, Principal principal) {
        Project project = projectService.getById(projectId);
        if (project == null) {
            redirect.addFlashAttribute("error", "Invalid Payload");
            return "redirect:/dashboard";
        }

        model.addAttribute("project", project);
        model.addAttribute("loggedUser", userService.findByUsername(principal.getName()));
        return "project-detail.jsp";
    }

    @GetMapping("/projects/{projectId}/edit")
    public String initEditForm(@PathVariable("projectId") long projectId, Model model, Principal principal) {
        model.addAttribute("loggedUser", userService.findByUsername(principal.getName()));
        model.addAttribute("project", projectService.getById(projectId));
        return "project-edit.jsp";
    }

    @PutMapping("/projects/{projectId}/edit")
    public String processEditProject(@PathVariable("projectId") long projectId,
            @Valid @ModelAttribute("project") Project project, BindingResult result) {
        if (result.hasErrors()) {
            return "project-edit.jsp";
        }
        project.getAssignedUsers().add(project.getLeader());
        projectService.save(project);

        return "redirect:/dashboard";
    }

    @DeleteMapping("/projects/{projectId}/delete")
    public String deleteProject(@PathVariable("projectId") long projectId) {
        projectService.delete(projectId);
        return "redirect:/dashboard";
    }

    @GetMapping("/projects/{projectId}/tasks")
    public String getProjectTasks(@PathVariable("projectId") long projectId, Principal principal,
            RedirectAttributes redirect, Model model) {

        Project project = projectService.getById(projectId);
        if (project == null) {
            redirect.addFlashAttribute("error", "Invalid Payload");
            return "redirect:/dashboard";
        }

        model.addAttribute("loggedUser", userService.findByUsername(principal.getName()));
        model.addAttribute("project", project);
        model.addAttribute("newTask", new Task());

        return "project-tasks-detail.jsp";
    }

    @PostMapping("/projects/{projectId}/tasks")
    public String createTask(@PathVariable("projectId") long projectId, @Valid @ModelAttribute("newTask") Task task,
            BindingResult result, Principal principal,
            RedirectAttributes redirect, Model model) {

        Project project = projectService.getById(projectId);
        if (project == null) {
            redirect.addFlashAttribute("error", "Invalid Payload");
            return "redirect:/dashboard";
        }

        if (result.hasErrors()) {
            model.addAttribute("loggedUser", userService.findByUsername(principal.getName()));
            model.addAttribute("project", project);
            return "project-tasks-detail.jsp";
        }

        taskService.save(task);

        return "redirect:/projects/" + project.getId() + "/tasks";
    }
}

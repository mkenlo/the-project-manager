package com.mkenlo.theprojectmanager.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "Firstname is required")
    @Size(min = 3, max = 30, message = "Firstname must be between 3 and 30 characters")
    String firstname;

    @NotEmpty(message = "Lastname is required")
    @Size(min = 3, max = 30, message = "Lastname must be between 3 and 30 characters")
    String lastname;

    @NotEmpty(message = "Email is required")
    @Email(message = "please enter a valid email")
    String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
    String password;

    @Transient
    @NotEmpty(message = "Firstname is required")
    @Size(min = 6, max = 30, message = "Confirm Password must be between 6 and 30 characters")
    String confirmPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project-members", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "projects_id"))
    List<Project> assignedProjects;

    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
    List<Project> projectsLed;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    List<Task> createdTasks;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(List<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    public List<Project> getProjectsLed() {
        return projectsLed;
    }

    public void setProjectsLed(List<Project> projectsLed) {
        this.projectsLed = projectsLed;
    }

    public List<Task> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(List<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }

}

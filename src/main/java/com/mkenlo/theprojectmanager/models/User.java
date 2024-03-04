package com.mkenlo.theprojectmanager.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NotEmpty
    @Size(min = 6, max = 10, message = "Username must be between 6 and 10 characters")
    private String username;

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
    @Size(min = 6, max = 128, message = "Password must be between 6 and 30 characters")
    String password;

    @Transient
    @NotEmpty(message = "Confirm Password is required")
    @Size(min = 6, max = 30, message = "Confirm Password must be between 6 and 30 characters")
    String confirmPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project-members", joinColumns = @JoinColumn(name = "teammember_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    Set<Project> assignedProjects;

    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
    Set<Project> projectsLed;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    Set<Task> createdTasks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User() {
        this.projectsLed = new HashSet<Project>();
        this.assignedProjects = new HashSet<Project>();
        this.createdTasks = new HashSet<Task>();
        this.roles = new ArrayList<>();
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

    public Set<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(HashSet<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    public Set<Project> getProjectsLed() {
        return projectsLed;
    }

    public void setProjectsLed(Set<Project> projectsLed) {
        this.projectsLed = projectsLed;
    }

    public Set<Task> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(Set<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAssignedProjects(Set<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}

package com.mkenlo.theprojectmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkenlo.theprojectmanager.models.Project;
import com.mkenlo.theprojectmanager.models.User;
import com.mkenlo.theprojectmanager.repositories.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository repo;

    public List<Project> getAll() {
        return repo.findAll();
    }

    public Project save(Project project) {
        return repo.save(project);
    }

    public Project getById(long id) {
        Optional<Project> optional = repo.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        return optional.get();
    }

    public List<Project> getByLead(User user) {
        return repo.findByLeader(user);
    }

    public List<Project> getByExcludingUser(long userId) {
        return repo.findByExcludingUser(userId);
    }
}

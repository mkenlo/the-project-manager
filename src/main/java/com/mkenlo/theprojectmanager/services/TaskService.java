package com.mkenlo.theprojectmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkenlo.theprojectmanager.models.Task;
import com.mkenlo.theprojectmanager.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    TaskRepository repo;

    public Task save(Task task) {
        return repo.save(task);
    }

    public List<Task> getAll() {
        return repo.findAll();
    }
}

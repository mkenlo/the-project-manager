package com.mkenlo.theprojectmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkenlo.theprojectmanager.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAll();

}

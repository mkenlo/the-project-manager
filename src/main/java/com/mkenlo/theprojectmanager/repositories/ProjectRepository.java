package com.mkenlo.theprojectmanager.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkenlo.theprojectmanager.models.Project;
import com.mkenlo.theprojectmanager.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    List<Project> findAll();

    Optional<Project> findById(Long id);

    List<Project> findByLeader(User user);

    @Query(value = "SELECT p.id, p.title, p.description,p.user_id, p.due_date FROM `projects` p inner JOIN `project-members` t ON p.id = t.project_id WHERE t.user_id != ?1 and p.user_id != ?1", nativeQuery = true)
    List<Project> findByExcludingUser(long userId);
}

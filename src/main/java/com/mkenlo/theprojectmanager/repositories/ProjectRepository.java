package com.mkenlo.theprojectmanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkenlo.theprojectmanager.models.Project;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    List<Project> findAll();

    Optional<Project> findById(Long id);

    List<Project> findByLeader(long userId);;
}

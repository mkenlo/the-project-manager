package com.mkenlo.theprojectmanager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mkenlo.theprojectmanager.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}

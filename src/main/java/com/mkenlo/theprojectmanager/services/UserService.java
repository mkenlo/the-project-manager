package com.mkenlo.theprojectmanager.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mkenlo.theprojectmanager.models.LoginUser;
import com.mkenlo.theprojectmanager.models.Role;
import com.mkenlo.theprojectmanager.models.User;
import com.mkenlo.theprojectmanager.repositories.RoleRepository;
import com.mkenlo.theprojectmanager.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    RoleRepository roleRepository;

    public User findById(long id) {
        Optional<User> optional = repo.findById(id);
        if (optional.isEmpty())
            return null;
        return optional.get();
    }

    public User createUser(User user, BindingResult result, boolean isAdmin) {

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            result.rejectValue("password", "match", "Confirm Password must Match Password");
        } else if (repo.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "exist", "Email already exist");
        }
        if (result.hasErrors())
            return null;
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        if (isAdmin) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
            }
            user.getRoles().add(adminRole);
        } else {
            Role userRole = roleRepository.findByName("ROLE_USER");
            if (userRole == null) {
                userRole = roleRepository.save(new Role("ROLE_USER"));
            }
            user.getRoles().add(userRole);
        }

        return repo.save(user);
    }

    public User doLogin(LoginUser user, BindingResult result) {

        Optional<User> potentialUser = repo.findByUsername(user.getUsername());
        if (!potentialUser.isPresent()) {
            result.rejectValue("username", "exist", "Username not found");
        } else if (!BCrypt.checkpw(user.getPassword(), potentialUser.get().getPassword()))
            result.rejectValue("password", "match", "Invalid Password");
        if (result.hasErrors())
            return null;
        return potentialUser.get();
    }

    public User findByUsername(String username) {
        Optional<User> optional = repo.findByUsername(username);
        return optional.get();
    }

}

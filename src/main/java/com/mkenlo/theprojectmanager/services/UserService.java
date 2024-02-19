package com.mkenlo.theprojectmanager.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mkenlo.theprojectmanager.models.LoginUser;
import com.mkenlo.theprojectmanager.models.User;
import com.mkenlo.theprojectmanager.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    public User findById(long id) {
        Optional<User> optional = repo.findById(id);
        if (optional.isEmpty())
            return null;
        return optional.get();
    }

    public User createUser(User user, BindingResult result) {

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            result.rejectValue("password", "match", "Confirm Password must Match Password");
        } else if (repo.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "exist", "Email already exist");
        }
        if (result.hasErrors())
            return null;
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return repo.save(user);
    }

    public User doLogin(LoginUser user, BindingResult result) {

        Optional<User> potentialUser = repo.findByEmail(user.getEmail());
        if (!potentialUser.isPresent()) {
            result.rejectValue("email", "exist", "Email not found");
        } else if (!BCrypt.checkpw(user.getPassword(), potentialUser.get().getPassword()))
            result.rejectValue("password", "match", "Invalid Password");
        if (result.hasErrors())
            return null;
        return potentialUser.get();
    }

}

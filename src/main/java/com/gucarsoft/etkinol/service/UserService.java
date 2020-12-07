package com.gucarsoft.etkinol.service;

import com.gucarsoft.etkinol.model.Message;
import com.gucarsoft.etkinol.model.user.CreateUser;
import com.gucarsoft.etkinol.model.user.Role;
import com.gucarsoft.etkinol.model.user.User;
import com.gucarsoft.etkinol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserRepository repo;

    public ResponseEntity create(User entity) {

        User createdEntity = repo.save(entity);
        if (createdEntity != null) {
            return new ResponseEntity<>(createdEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<List<User>> list() {
        List<User> entityList = repo.findAll();
        return new ResponseEntity<>(entityList, HttpStatus.OK);
    }

    public ResponseEntity<User> getById(long id) {
        User entity = repo.findById(id).orElse(null);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteById(long id) {
        User entity = repo.findById(id).orElse(null);
        return deleteById(entity, repo);
    }

    public ResponseEntity<User> getByUsername(String username) {
        User user = repo.findByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateProfile(String authUserName, User user) {
        User originalUser = repo.findByUsername(authUserName);
        originalUser.setName(user.getName());
        originalUser.setSurname(user.getSurname());
        originalUser.setEmail(user.getEmail());
        repo.save(originalUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity register(CreateUser user) {
        User entity = new User();
        User existingUsername = repo.findByUsername(user.getUsername());
        if (existingUsername != null) {
            return new ResponseEntity<>(Message.USERNAME_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        User existingEmail = repo.findByEmail(user.getEmail());
        if (existingEmail != null) {
            return new ResponseEntity<>(Message.EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        // assign user role
        entity.setRole(Role.USER);
        entity.setEmail(user.getEmail());
        entity.setName(user.getName());
        entity.setSurname(user.getSurname());
        entity.setPassword(user.getPassword());
        entity.setUsername(user.getUsername());
        User savedUser = repo.save(entity);
        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Message.SYSTEM_ERROR, HttpStatus.CONFLICT);
        }
    }





}

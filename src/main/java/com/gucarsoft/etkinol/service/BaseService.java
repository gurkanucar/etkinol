package com.gucarsoft.etkinol.service;


import com.gucarsoft.etkinol.model.BaseEntity;
import com.gucarsoft.etkinol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;


public class BaseService {

    @Autowired
    protected UserRepository userRepo;


    protected String getAuthUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    ResponseEntity deleteById(BaseEntity entity, JpaRepository repo) {
        if (entity != null) {
            entity.setDeleted(true);
            repo.save(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

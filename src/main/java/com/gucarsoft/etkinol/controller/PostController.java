package com.gucarsoft.etkinol.controller;

import com.gucarsoft.etkinol.model.Post;
import com.gucarsoft.etkinol.model.dto.commentDTO;
import com.gucarsoft.etkinol.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService service;

    @GetMapping
    public ResponseEntity list() {return service.getList();}

    @GetMapping("/{id}")
    public ResponseEntity findByID(Long id) {
        return service.findByID(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Post entity) {
        return service.create(entity);
    }

    @PostMapping("/comment")
    public ResponseEntity createComment(@RequestBody commentDTO entity) {
        return service.createComment(entity);
    }

    @DeleteMapping("/comment")
    public ResponseEntity deleteComment(@RequestBody commentDTO entity) {
        return service.deleteComment(entity);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Post entity) {
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@RequestParam Long id) {
        return service.delete(id);
    }


}

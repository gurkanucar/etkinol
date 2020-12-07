package com.gucarsoft.etkinol.service;


import com.gucarsoft.etkinol.model.Comment;
import com.gucarsoft.etkinol.model.Post;
import com.gucarsoft.etkinol.model.dto.commentDTO;
import com.gucarsoft.etkinol.repository.CommentRepository;
import com.gucarsoft.etkinol.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService extends BaseService {

    @Autowired
    PostRepository repository;

    @Autowired
    CommentRepository commentRepo;

    public ResponseEntity getList() {
        List<Post> posts = repository.findAll();
        return new ResponseEntity(posts, HttpStatus.OK);
    }

    public ResponseEntity findByID(Long id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity create(Post entity) {
        return new ResponseEntity(repository.save(entity), HttpStatus.OK);
    }

    public ResponseEntity createComment(commentDTO entity) {
        Post post= repository.findById(entity.getPost().getId()).get();
        Comment comment = entity.getComment();
        commentRepo.save(comment);
        List<Comment> comments = post.getComments();
        comments.add(comment);
        post.setComments(comments);
        return new ResponseEntity(repository.save(post), HttpStatus.OK);
    }
    public ResponseEntity deleteComment(commentDTO entity) {
        Post post= repository.findById(entity.getPost().getId()).get();
        Comment comment = commentRepo.findById(entity.getComment().getId()).get();
        comment.setDeleted(true);
        commentRepo.save(comment);
        return new ResponseEntity(repository.save(post), HttpStatus.OK);
    }
    public ResponseEntity update(Post entity) {
        Post existing = repository.findById(entity.getId()).get();
        return new ResponseEntity(repository.save(existing), HttpStatus.OK);
    }

    public ResponseEntity delete(Long id) {
        Post entity = repository.findById(id).get();
        repository.delete(entity);
        return new ResponseEntity(HttpStatus.OK);
    }


}

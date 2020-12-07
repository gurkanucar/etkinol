package com.gucarsoft.etkinol.repository;


import com.gucarsoft.etkinol.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

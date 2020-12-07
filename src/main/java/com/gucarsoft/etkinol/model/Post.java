package com.gucarsoft.etkinol.model;

import com.gucarsoft.etkinol.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "post")
public class Post extends BaseEntity {
    @OneToOne
    private User user;
    private String text;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
    private Long likeCount;

}

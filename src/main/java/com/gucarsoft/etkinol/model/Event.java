package com.gucarsoft.etkinol.model;

import com.gucarsoft.etkinol.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "event")
public class Event extends BaseEntity {
    @OneToOne
    User user;
    private String text;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Category> categories;

}

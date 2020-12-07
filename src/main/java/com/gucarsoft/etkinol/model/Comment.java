package com.gucarsoft.etkinol.model;

import com.gucarsoft.etkinol.model.user.User;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "comment")
@Where(clause = "deleted=false")
public class Comment extends BaseEntity {
    @OneToOne
    User user;
    String comment;
}

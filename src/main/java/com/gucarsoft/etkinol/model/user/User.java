package com.gucarsoft.etkinol.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gucarsoft.etkinol.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Data
@Entity
@Table(name="user")
public class User extends BaseEntity {

    private String username;

    @Setter(AccessLevel.NONE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String name;
    private String surname;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

}

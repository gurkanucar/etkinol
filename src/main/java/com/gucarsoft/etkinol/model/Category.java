package com.gucarsoft.etkinol.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "category")
public class Category extends BaseEntity{
    String name;
}

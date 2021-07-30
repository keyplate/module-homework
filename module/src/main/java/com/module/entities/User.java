package com.module.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
public class User {
    @Id
    private Long id;
    private String email;
}

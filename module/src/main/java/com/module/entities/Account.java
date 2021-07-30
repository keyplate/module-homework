package com.module.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="account")
public class Account {
    @Id
    private Long id;
    private Long ownerId;
}

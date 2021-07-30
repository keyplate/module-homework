package com.module.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Operation {
    @Id
    private Long id;
    private Long accountId;
}

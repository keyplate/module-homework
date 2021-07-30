package com.module.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="income")
public class IncomeCategory {
    @Id
    private Long id;
    private Long operationId;
}

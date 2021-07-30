package com.module.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExpensesCategory {
    @Id
    private Long id;
    private Long operationId;

    @Deprecated
    protected ExpensesCategory() {}
}

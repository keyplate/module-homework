package com.module.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "expenses_category")
@Getter
@Setter
public class ExpensesCategory extends OperationCategory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    private Operation operation;


    protected ExpensesCategory() {}

    public ExpensesCategory(Operation operation) {
        this.operation = operation;
    }
}

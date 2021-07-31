package com.module.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "operation")
@Getter
@Setter
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "operation")
    private List<ExpensesCategory> expenses;

    @OneToMany(mappedBy = "operation")
    private List<IncomeCategory> income;

    @Column(name = "operation_sum")
    private Long operationSum;

    @Column(name = "operation_time")
    private Instant operationTime;

    protected Operation() {}

    public Operation(Account account, Long operationSum, Instant operationTime) {
        this.account = account;
        this.operationSum = operationSum;
        this.operationTime = operationTime;
    }
}

package com.module.service;

import com.module.entities.*;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.time.Instant;

public class AddOperationService {

    private final Session persistence;
    private final static Logger AddOperationServiceLogger = LoggerFactory.getLogger(AddOperationService.class);

    public AddOperationService(Session session) {
        this.persistence = session;
    }

    public void addExpense(Account account, Long sum) {
        if(sum >= 0) throw new RuntimeException("Wrong values of operation for this category");
        persistence.beginTransaction();
        AddOperationServiceLogger.debug("Account " + account.getId() + " adding expense start of transaction");
        Operation operation = new Operation(account, sum, Instant.now());
        ExpensesCategory expenses = new ExpensesCategory(operation);
        persistence.save(operation);
        persistence.save(expenses);
        persistence.getTransaction().commit();
        AddOperationServiceLogger.debug("Account " + account.getId() + " added expense " + sum);
    }

    public void addIncome(Account account, Long sum) {
        if(sum <= 0) throw new RuntimeException("Wrong values of operation for this category");
        persistence.beginTransaction();
        AddOperationServiceLogger.debug("Account " + account.getId() + " adding income start of transaction");
        Operation operation = new Operation(account, sum, Instant.now());
        IncomeCategory income = new IncomeCategory(operation);
        persistence.save(operation);
        persistence.save(income);
        persistence.getTransaction().commit();
        AddOperationServiceLogger.debug("Account " + account.getId() + " added income " + sum);
    }
}

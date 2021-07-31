package com.module.controller;

import com.module.entities.Account;
import com.module.entities.User;
import com.module.service.AddOperationService;
import com.module.service.OperationsExporter;
import org.hibernate.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;


public class Application {

    private Connection con;
    private Session session;
    private User usr;

    public Application(User user, Connection connection, Session session) {
        this.con = connection;
        this.session = session;
        this.usr = user;
    }

    public void start() {
        printUserMenu();
        chooseAddOrSave();
    }

    private void printUserAccounts(String accounts) {
        System.out.println("Choose account: " + accounts);
    }

    private void printUserMenu() {
        System.out.println("1 - add operation\n" +
                "2 - Save operations to operations.csv");
    }

    private long askUserAccount() {
        printUserAccounts(usr.getAccounts().toString());
        System.out.println("Choose account by id");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Long accountId = Long.parseLong(reader.readLine());
            return accountId;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void chooseAddOrSave() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int usrChoice = Integer.parseInt(reader.readLine());
            if (usrChoice == 1) {
                save();
            } else if(usrChoice == 2) {
                export();
            } else {throw new RuntimeException("Wrong input");}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void save() {
        AddOperationService add = new AddOperationService(session);
        Long accountId = askUserAccount();
        for(Account a : usr.getAccounts()) {
            if(a.getId() == accountId) {
                addExpenseOrIncome(add, a);
            }
        }
    }

    private void export() {
        OperationsExporter exporter = new OperationsExporter(con);
        Long accountId = askUserAccount();
        for(Account a : usr.getAccounts()) {
            if(a.getId() == accountId) {
                exporter.export(a);
            }
        }
    }

    private void addExpenseOrIncome(AddOperationService service, Account account) {
        System.out.println("1 - To add Expense\n" +
                "2 - To add Income");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int choice = Integer.parseInt(reader.readLine());
            System.out.println("Now enter operation value");
            long value = Long.parseLong(reader.readLine());
            if(choice == 1) {
                service.addExpense(account, value);
            }else if(choice == 2) {
                service.addIncome(account, value);
            }else {
                throw new RuntimeException("Wrong input");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

package com.module;

import com.module.controller.Application;
import com.module.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.setProperty("hibernate.connection.username", args[1]);
        cfg.setProperty("hibernate.connection.password", args[1]);

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .getMetadataBuilder()
                .build();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.
                getConnection(args[3], args[1], args[2]);
             SessionFactory sessionFactory = metadata.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            TypedQuery<User> query = session.createQuery("from User where email = ?1");
            query.setParameter(1, args[0]);
            User user = query.getSingleResult();
            Application app = new Application(user, connection, session);
            app.start();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

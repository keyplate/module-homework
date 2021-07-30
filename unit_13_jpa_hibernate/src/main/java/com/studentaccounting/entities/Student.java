package com.studentaccounting.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id", referencedColumnName = "id")
    private Group group;

    @OneToMany(mappedBy = "student")
    private Set<Grade> grades;
    protected Student() {}

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

}

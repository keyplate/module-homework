package com.studentaccounting.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "grade")
@Getter
@Setter
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id", unique = true)
    private Topic topic;


    private int grade;

    protected Grade() {}

    public Grade(Topic topic, Student student, int grade) {
        this.topic = topic;
        this.student = student;
        this.grade = grade;
    }
}

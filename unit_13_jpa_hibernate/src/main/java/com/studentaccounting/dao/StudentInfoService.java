package com.studentaccounting.dao;

import com.studentaccounting.entities.Lesson;
import com.studentaccounting.entities.Student;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class StudentInfoService {

    private final EntityManager persistence;

    public StudentInfoService(EntityManager persistence) {
        this.persistence = persistence;
    }

    public String getStudentInfo(Long studentId) {
        Student student = findStudent(studentId);
        Lesson lesson = findLesson(student);
        return buildStudentInfo(student, lesson);
    }

    private Lesson findLesson(Student student) {
        TypedQuery<Lesson> query = persistence.createQuery("select l from Lesson l where group = :id and date > current_timestamp order by date", Lesson.class);
        query.setMaxResults(1);
        query.setParameter("id", student.getGroup());
        return (Lesson) query.getSingleResult();
    }


    private Student findStudent(Long studentId) {
        TypedQuery<Student> query = persistence.createQuery("select s from Student s where id = :student", Student.class);
        query.setParameter("student", studentId);
        return query.getSingleResult();
    }

    private String buildStudentInfo(Student student, Lesson lesson) {
        String studentInfo =
                "name = " + student.getName()
                        + "\nage = " + student.getAge()
                        + "\ngroupId = " + student.getGroup().getId()
                        + "\nfaculty name = " + student.getGroup().getFaculty().getName()
                        + "\nlesson date = " + lesson.getDate()
                        + "\ntopic = " + lesson.getTopic().getName();
        return studentInfo;
    }
}

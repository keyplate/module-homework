package com.studentaccounting.dao;

import com.studentaccounting.entities.Grade;
import com.studentaccounting.entities.Lesson;
import com.studentaccounting.entities.Student;
import com.studentaccounting.entities.Topic;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class StudentInfoService {

    private EntityManager persistence;

    public StudentInfoService(EntityManager persistence) {
        this.persistence = persistence;
    }

    public String getStudentInfo(Long studentId) {
        Student student = findStudent(studentId);
        Lesson lesson = findLesson(student);
        Grade grade = findGrade(student, lesson.getTopic());
        return buildStudentInfo(student, lesson, grade);
    }

    private Lesson findLesson(Student student) {
        Query query = persistence.createQuery("from Lesson where group = :id order by date");
        query.setParameter("id", student.getGroup());
        return (Lesson) query.getSingleResult();
    }

    private Student findStudent(Long studentId) {
        Query query = persistence.createQuery("from Student where id = :student");
        query.setParameter("student", studentId);
        return (Student) query.getSingleResult();
    }

    private Grade findGrade(Student student, Topic topic) {
        Query query = persistence.createQuery("from Grade where student = :stud and topic = :topic");
        query.setParameter("stud", student);
        query.setParameter("topic", topic);
        return (Grade) query.getSingleResult();
    }

    private String buildStudentInfo(Student student, Lesson lesson, Grade grade) {
        String studentInfo =
                "name = " + student.getName()
                        + "\nage = " + student.getAge()
                        + "\ngroupId = " + student.getGroup().getId()
                        + "\nfaculty name = " + student.getGroup().getFaculty().getName()
                        + "\nlesson date = " + lesson.getDate()
                        + "\ntopic = " + lesson.getTopic().getName()
                        + "\ngrade = " + grade.getGrade();
        return studentInfo;
    }
}

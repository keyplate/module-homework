package com.studentaccounting.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "topics")
@Getter
@Setter
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "topic")
    private Set<Lesson> lessons;

    protected Topic() {}

    public Topic(String name) {
        this.name = name;
    }

}

package com.studentaccounting.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="faculty")
@Getter
@Setter
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<Group> groups;

    protected Faculty() {}

    public Faculty(String name) {
        this.id = id;
        this.name = name;
    }
}

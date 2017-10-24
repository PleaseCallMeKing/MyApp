package com.augwit.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Cource.
 */
@Entity
@Table(name = "cource")
public class Cource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cource_id")
    private Long courceId;

    @Column(name = "cource_name")
    private String courceName;

    @OneToOne(mappedBy = "cource")
    @JsonIgnore
    private Teacher teacher;

    @ManyToMany(mappedBy = "cources")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourceId() {
        return courceId;
    }

    public Cource courceId(Long courceId) {
        this.courceId = courceId;
        return this;
    }

    public void setCourceId(Long courceId) {
        this.courceId = courceId;
    }

    public String getCourceName() {
        return courceName;
    }

    public Cource courceName(String courceName) {
        this.courceName = courceName;
        return this;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Cource teacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Cource students(Set<Student> students) {
        this.students = students;
        return this;
    }

    public Cource addStudent(Student student) {
        this.students.add(student);
        student.getCources().add(this);
        return this;
    }

    public Cource removeStudent(Student student) {
        this.students.remove(student);
        student.getCources().remove(this);
        return this;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cource cource = (Cource) o;
        if (cource.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cource.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cource{" +
            "id=" + getId() +
            ", courceId='" + getCourceId() + "'" +
            ", courceName='" + getCourceName() + "'" +
            "}";
    }
}

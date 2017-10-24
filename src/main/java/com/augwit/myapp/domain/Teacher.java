package com.augwit.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Teacher.
 */
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_no")
    private Long teacherNo;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_phone")
    private Long teacherPhone;

    @OneToOne
    @JoinColumn(unique = true)
    private Cource cource;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherNo() {
        return teacherNo;
    }

    public Teacher teacherNo(Long teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    public void setTeacherNo(Long teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public Teacher teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getTeacherPhone() {
        return teacherPhone;
    }

    public Teacher teacherPhone(Long teacherPhone) {
        this.teacherPhone = teacherPhone;
        return this;
    }

    public void setTeacherPhone(Long teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Cource getCource() {
        return cource;
    }

    public Teacher cource(Cource cource) {
        this.cource = cource;
        return this;
    }

    public void setCource(Cource cource) {
        this.cource = cource;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Teacher students(Set<Student> students) {
        this.students = students;
        return this;
    }

    public Teacher addStudent(Student student) {
        this.students.add(student);
        student.setTeacher(this);
        return this;
    }

    public Teacher removeStudent(Student student) {
        this.students.remove(student);
        student.setTeacher(null);
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
        Teacher teacher = (Teacher) o;
        if (teacher.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacher.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Teacher{" +
            "id=" + getId() +
            ", teacherNo='" + getTeacherNo() + "'" +
            ", teacherName='" + getTeacherName() + "'" +
            ", teacherPhone='" + getTeacherPhone() + "'" +
            "}";
    }
}

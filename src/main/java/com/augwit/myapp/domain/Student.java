package com.augwit.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_no")
    private Long studentNo;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_score")
    private Long studentScore;

    @ManyToMany
    @JoinTable(name = "student_cource",
               joinColumns = @JoinColumn(name="students_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="cources_id", referencedColumnName="id"))
    private Set<Cource> cources = new HashSet<>();

    @ManyToOne
    private Teacher teacher;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentNo() {
        return studentNo;
    }

    public Student studentNo(Long studentNo) {
        this.studentNo = studentNo;
        return this;
    }

    public void setStudentNo(Long studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public Student studentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getStudentScore() {
        return studentScore;
    }

    public Student studentScore(Long studentScore) {
        this.studentScore = studentScore;
        return this;
    }

    public void setStudentScore(Long studentScore) {
        this.studentScore = studentScore;
    }

    public Set<Cource> getCources() {
        return cources;
    }

    public Student cources(Set<Cource> cources) {
        this.cources = cources;
        return this;
    }

    public Student addCource(Cource cource) {
        this.cources.add(cource);
        cource.getStudents().add(this);
        return this;
    }

    public Student removeCource(Cource cource) {
        this.cources.remove(cource);
        cource.getStudents().remove(this);
        return this;
    }

    public void setCources(Set<Cource> cources) {
        this.cources = cources;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Student teacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
        Student student = (Student) o;
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", studentNo='" + getStudentNo() + "'" +
            ", studentName='" + getStudentName() + "'" +
            ", studentScore='" + getStudentScore() + "'" +
            "}";
    }
}

package com.augwit.myapp.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Student entity.
 */
public class StudentDTO implements Serializable {

    private Long id;

    private Long studentNo;

    private String studentName;

    private Long studentScore;

    private Set<CourceDTO> cources = new HashSet<>();

    private Long teacherId;

    private String teacherTeacherNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Long studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(Long studentScore) {
        this.studentScore = studentScore;
    }

    public Set<CourceDTO> getCources() {
        return cources;
    }

    public void setCources(Set<CourceDTO> cources) {
        this.cources = cources;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherTeacherNo() {
        return teacherTeacherNo;
    }

    public void setTeacherTeacherNo(String teacherTeacherNo) {
        this.teacherTeacherNo = teacherTeacherNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentDTO studentDTO = (StudentDTO) o;
        if(studentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
            "id=" + getId() +
            ", studentNo='" + getStudentNo() + "'" +
            ", studentName='" + getStudentName() + "'" +
            ", studentScore='" + getStudentScore() + "'" +
            "}";
    }
}

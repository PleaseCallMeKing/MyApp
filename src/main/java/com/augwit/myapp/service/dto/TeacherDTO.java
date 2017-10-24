package com.augwit.myapp.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Teacher entity.
 */
public class TeacherDTO implements Serializable {

    private Long id;

    private Long teacherNo;

    private String teacherName;

    private Long teacherPhone;

    private Long courceId;

    private String courceCourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(Long teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(Long teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Long getCourceId() {
        return courceId;
    }

    public void setCourceId(Long courceId) {
        this.courceId = courceId;
    }

    public String getCourceCourceId() {
        return courceCourceId;
    }

    public void setCourceCourceId(String courceCourceId) {
        this.courceCourceId = courceCourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeacherDTO teacherDTO = (TeacherDTO) o;
        if(teacherDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacherDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
            "id=" + getId() +
            ", teacherNo='" + getTeacherNo() + "'" +
            ", teacherName='" + getTeacherName() + "'" +
            ", teacherPhone='" + getTeacherPhone() + "'" +
            "}";
    }
}

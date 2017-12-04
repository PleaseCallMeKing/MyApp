package com.augwit.myapp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 自助学员收藏课程
 * @author Carl Wang
 */
@ApiModel(description = "学员必修课程 @author Carl Wang")
@Entity
@Table(name = "lms_student_collect_course")
public class StudentCollectCourse implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学员Id
     * */
    @NotNull
    @Column(name = "user_id")
    private Long userId;

    /**
     * 学员姓名
     * */
    @NotNull
    @Column(name = "user_name")
    private String userName;

    /**
     * 课程Id
     * */
    @NotNull
    @Column(name = "course_id")
    private Long courseId;

    /**
     * 课程名
     * */
    @NotNull
    @Column(name = "course_name")
    private String courseName;

    /**
     * 单位Id
     * */
    private Long oriId;

    /**
     * 单位名称
     * */
    private String oriName;

    /**
     * 收藏时间
     * */
    @NotNull
    @ApiModelProperty(value = "收藏时间")
    @Column(name = "collect_date")
    private LocalDate collectDate ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public StudentCollectCourse userId(Long userId){
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public StudentCollectCourse userName(String userName){
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public StudentCollectCourse courseId(Long courseId){
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public StudentCollectCourse courseName(String courseName){
        this.courseName = courseName;
        return this;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getCollectDate() {
        return collectDate;
    }

    public StudentCollectCourse collectCourse(LocalDate collectDate){
        this.collectDate = collectDate;
        return this;
    }

    public void setCollectDate(LocalDate collectDate) {
        this.collectDate = collectDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentCollectCourse studentCollectCourse = (StudentCollectCourse) o;
        if (studentCollectCourse.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentCollectCourse.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentCollectCourse{" +
            "id=" + id +
            ", userId=" + userId +
            ", userName='" + userName + '\'' +
            ", courseId=" + courseId +
            ", courseName='" + courseName + '\'' +
            ", collectDate=" + collectDate +
            '}';
    }
}



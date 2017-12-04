package com.augwit.myapp.web.rest;

import com.augwit.myapp.domain.StudentCollectCourse;
import com.augwit.myapp.service.StudentCollectCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class StudentCollectCourseResource {

    private final Logger log = LoggerFactory.getLogger(StudentCollectCourseResource.class);

    private final StudentCollectCourseService studentCollectCourseService;

    public StudentCollectCourseResource(StudentCollectCourseService studentCollectCourseService) {
        this.studentCollectCourseService = studentCollectCourseService;
    }

    @GetMapping("/studentCollectCourse")
    public StudentCollectCourse collectCourses(){
        studentCollectCourseService.hashCode();
        return new StudentCollectCourse();
    }

}

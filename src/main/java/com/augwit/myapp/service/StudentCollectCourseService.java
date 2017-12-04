package com.augwit.myapp.service;

import com.augwit.myapp.domain.StudentCollectCourse;
import com.augwit.myapp.repository.StudentCollectCourseRepository;
import org.apache.http.util.TextUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.awt.print.Pageable;

@Service
@Transactional
public class StudentCollectCourseService {

    private StudentCollectCourseRepository studentCollectCourseRepository;



    public void findAll(Long id, Pageable pageable){
        studentCollectCourseRepository.findAll(new Specification<StudentCollectCourse>() {
            @Override
            public Predicate toPredicate(Root<StudentCollectCourse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Long> idPath = root.get("");

                query.where(cb.c);
                return null;
            }
        });
    }


}

package com.augwit.myapp.repository;

import com.augwit.myapp.domain.StudentCollectCourse;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface StudentCollectCourseRepository extends JpaRepository<StudentCollectCourse, Long> {
    Page<StudentCollectCourse> findAll(Specification<StudentCollectCourse> spec, Pageable pageable);
    List<StudentCollectCourse> findAll(Specification<StudentCollectCourse> spec);

}

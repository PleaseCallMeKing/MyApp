package com.augwit.myapp.repository;

import com.augwit.myapp.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.stream.Stream;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c")
    Stream<City> findAllByCustomQueryAndStream();

    @Query("select c from City c")
    Stream<City> streamAllPaged(Pageable pageable);



}

package com.augwit.myapp.repository;

import com.augwit.myapp.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}

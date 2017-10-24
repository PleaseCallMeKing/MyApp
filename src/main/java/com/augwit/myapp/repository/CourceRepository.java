package com.augwit.myapp.repository;

import com.augwit.myapp.domain.Cource;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Cource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourceRepository extends JpaRepository<Cource, Long> {

}

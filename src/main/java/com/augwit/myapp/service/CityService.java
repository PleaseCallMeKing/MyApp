package com.augwit.myapp.service;

import com.augwit.myapp.domain.City;
import com.augwit.myapp.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CityService {

    private CityRepository cityRepository ;

    public void save(City city){
        cityRepository.save(city);
    }

}

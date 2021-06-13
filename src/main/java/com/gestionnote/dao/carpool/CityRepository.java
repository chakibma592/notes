package com.gestionnote.dao.carpool;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionnote.entities.carpool.City;

public interface CityRepository extends JpaRepository<City, Long>{

}

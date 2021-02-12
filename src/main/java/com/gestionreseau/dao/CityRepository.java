package com.gestionreseau.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionreseau.entities.City;

public interface CityRepository extends JpaRepository<City, Long>{

}

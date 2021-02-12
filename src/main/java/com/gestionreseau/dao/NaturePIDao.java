package com.gestionreseau.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gestionreseau.entities.NaturePI;

@Repository
public interface NaturePIDao extends PagingAndSortingRepository<NaturePI, Integer> {


	


}

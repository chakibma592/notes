package com.gestionnote.dao.carpool;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.carpool.NaturePI;

@Repository
public interface NaturePIDao extends PagingAndSortingRepository<NaturePI, Integer> {


	


}

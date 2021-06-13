package com.gestionnote.dao.carpool;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.carpool.PointInteret;


@Repository
public interface PointInteretDao extends PagingAndSortingRepository<PointInteret, Integer> {

}
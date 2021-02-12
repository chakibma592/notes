package com.gestionreseau.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gestionreseau.entities.PointInteret;


@Repository
public interface PointInteretDao extends PagingAndSortingRepository<PointInteret, Integer> {

}
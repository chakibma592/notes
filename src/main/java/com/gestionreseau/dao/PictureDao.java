package com.gestionreseau.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gestionreseau.entities.Picture;



@Repository
public interface PictureDao extends PagingAndSortingRepository<Picture, Integer> {

}

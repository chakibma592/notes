package com.gestionnote.dao.carpool;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.carpool.Picture;



@Repository
public interface PictureDao extends PagingAndSortingRepository<Picture, Integer> {

}

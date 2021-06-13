package com.gestionnote.dao.carpool;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestionnote.entities.carpool.Posts;

public interface PostsRepository extends PagingAndSortingRepository<Posts, Long>{
}

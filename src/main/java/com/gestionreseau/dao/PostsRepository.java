package com.gestionreseau.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestionreseau.entities.Posts;

public interface PostsRepository extends PagingAndSortingRepository<Posts, Long>{
}

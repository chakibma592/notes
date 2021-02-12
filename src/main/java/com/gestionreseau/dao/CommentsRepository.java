package com.gestionreseau.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestionreseau.entities.Comments;

public interface CommentsRepository extends PagingAndSortingRepository<Comments, Long>{
	Page<Comments> findByPostsId(Long postsId, Pageable pageable);
}

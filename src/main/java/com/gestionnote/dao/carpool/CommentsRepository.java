package com.gestionnote.dao.carpool;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestionnote.entities.carpool.Comments;

public interface CommentsRepository extends PagingAndSortingRepository<Comments, Long>{
	Page<Comments> findByPostsId(Long postsId, Pageable pageable);
}

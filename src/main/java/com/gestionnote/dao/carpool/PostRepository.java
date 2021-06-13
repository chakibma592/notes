package com.gestionnote.dao.carpool;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.carpool.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}

package com.gestionreseau.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionreseau.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}

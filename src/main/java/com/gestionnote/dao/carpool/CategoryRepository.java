package com.gestionnote.dao.carpool;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionnote.entities.carpool.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}

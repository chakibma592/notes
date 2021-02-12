package com.gestionreseau.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionreseau.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}

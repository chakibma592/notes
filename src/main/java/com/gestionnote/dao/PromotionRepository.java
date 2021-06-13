package com.gestionnote.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestionnote.entities.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>{

}

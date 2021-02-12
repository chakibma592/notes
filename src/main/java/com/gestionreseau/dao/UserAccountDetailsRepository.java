package com.gestionreseau.dao;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestionreseau.entities.UserAccountDetails;

public interface UserAccountDetailsRepository extends PagingAndSortingRepository<UserAccountDetails, Long>{

}

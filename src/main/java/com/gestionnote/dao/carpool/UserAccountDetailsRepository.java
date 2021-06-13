package com.gestionnote.dao.carpool;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestionnote.entities.carpool.UserAccountDetails;

public interface UserAccountDetailsRepository extends PagingAndSortingRepository<UserAccountDetails, Long>{

}

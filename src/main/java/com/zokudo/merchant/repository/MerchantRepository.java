package com.zokudo.merchant.repository;

import com.zokudo.merchant.model.MerchantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MerchantRepository extends JpaRepository<MerchantModel,Long> {

    @Query(value = "select * from merchant where email = :email limit 1",nativeQuery = true)
    MerchantModel findMerchantByEmail(@Param("email") String email);
}

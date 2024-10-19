package com.example.PrestaBanco.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.PrestaBanco.entities.CreditApplicationEntity;

import java.util.List;

@Repository
public interface CreditApplicationRepository  extends JpaRepository<CreditApplicationEntity, Long> {

    @Query("SELECT c FROM CreditApplicationEntity c WHERE c.client_id = :client_id")
    List<CreditApplicationEntity> findByClientId(@Param("client_id") Long client_id);

    @Query("SELECT c FROM CreditApplicationEntity c WHERE c.credit_application_id = :credit_application_id")
    CreditApplicationEntity findByCreditApplicationId(@Param("credit_application_id") Long credit_application_id);

    @Query("SELECT c FROM CreditApplicationEntity c WHERE c.name = :name")
    CreditApplicationEntity findByName(@Param("name") String name);

    @Query("SELECT c FROM CreditApplicationEntity c WHERE c.credit_date = :credit_date")
    CreditApplicationEntity findByCreditDate(@Param("credit_date") String credit_date);

    @Query("SELECT c FROM CreditApplicationEntity c WHERE c.document_1 = :document_1")
    CreditApplicationEntity findByDocument1(@Param("document_1") boolean document_1);

    @Query("SELECT c FROM CreditApplicationEntity c WHERE c.document_2 = :document_2")
    CreditApplicationEntity findByDocument2(@Param("document_2") boolean document_2);

    @Query("SELECT c FROM CreditApplicationEntity c WHERE c.document_3 = :document_3")
    CreditApplicationEntity findByDocument3(@Param("document_3") boolean document_3);

    @Query("SELECT c FROM CreditApplicationEntity c WHERE c.document_4 = :document_4")
    CreditApplicationEntity findByDocument4(@Param("document_4") boolean document_4);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM CreditApplicationEntity c WHERE c.client_id = :client_id")
    boolean existsByClientId(Long client_id);


}

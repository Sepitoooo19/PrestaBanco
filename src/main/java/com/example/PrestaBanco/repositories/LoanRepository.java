package com.example.PrestaBanco.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.PrestaBanco.entities.LoanEntity;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {


    @Query("SELECT l FROM LoanEntity l WHERE l.loan_amount = :loan_amount")
    LoanEntity findByAmount(@Param("loan_amount") double loan_amount);

    @Query("SELECT l FROM LoanEntity l WHERE l.max_time_limit = :max_time_limit")
    LoanEntity findByTimeLimit(@Param("max_time_limit") int max_time_limit);

    @Query("SELECT l FROM LoanEntity l WHERE l.min_interest_rate = :min_interest_rate")
    LoanEntity findByInterestRate(@Param("min_interest_rate") double min_interest_rate);

    @Query("SELECT l FROM LoanEntity l WHERE l.loan_id = :loan_id")
    LoanEntity findByLoanId(@Param("loan_id") Long loan_id);

    @Query("SELECT l FROM LoanEntity l WHERE l.client_id = :client_id")
    List<LoanEntity> findByClientId(@Param("client_id") Long client_id);

    @Query("SELECT l FROM LoanEntity l WHERE l.loan_type = :loan_type")
    LoanEntity findByLoanType(@Param("loan_type") String loan_type);

    @Query("SELECT l FROM LoanEntity l WHERE l.loan_date = :loan_date")
    LoanEntity findByLoanDate(@Param("loan_date") String loan_date);

    @Query("SELECT l FROM LoanEntity l WHERE l.loan_status = :loan_status")
    LoanEntity findByLoanStatus(@Param("loan_status") String loan_status);

    @Query("SELECT l FROM LoanEntity l WHERE l.max_interest_rate = :max_interest_rate")
    LoanEntity findByMaxInterestRate(@Param("max_interest_rate") double max_interest_rate);

    @Query("SELECT l FROM LoanEntity l WHERE l.max_financing_amount = :max_financing_amount")
    LoanEntity findByMaxFinancingAmount(@Param("max_financing_amount") double max_financing_amount);

    @Query("SELECT l FROM LoanEntity l WHERE l.credit_application_id = :credit_application_id")
    LoanEntity findByCreditApplicationId(@Param("credit_application_id") Long credit_application_id);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END FROM LoanEntity l WHERE l.loan_id = :loan_id")
    boolean existsByLoanId(Long loan_id);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END FROM LoanEntity l WHERE l.client_id = :client_id")
    boolean existsByClientId(Long client_id);

    @Query("SELECT l FROM LoanEntity l WHERE l.client_id = :client_id AND l.loan_type = :loanType")
    LoanEntity findByClientIdAndLoanType(Long client_id, String loanType);

}
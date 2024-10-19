package com.example.PrestaBanco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PrestaBanco.repositories.LoanRepository;
import com.example.PrestaBanco.entities.LoanEntity;
import java.util.List;


@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<LoanEntity> findAll() {
        return loanRepository.findAll();
    }

    public LoanEntity findByLoanId(Long loan_id) {
        return loanRepository.findByLoanId(loan_id);
    }

    public LoanEntity findByAmount(double loan_amount) {
        return loanRepository.findByAmount(loan_amount);
    }

    public LoanEntity findByTimeLimit(int max_time_limit) {
        return loanRepository.findByTimeLimit(max_time_limit);
    }

    public LoanEntity findByInterestRate(double min_interest_rate) {
        return loanRepository.findByInterestRate(min_interest_rate);
    }


    public LoanEntity findByLoanType(String loan_type) {
        return loanRepository.findByLoanType(loan_type);
    }

    public LoanEntity findByLoanDate(String loan_date) {
        return loanRepository.findByLoanDate(loan_date);
    }

    public LoanEntity findByLoanStatus(String loan_status) {
        return loanRepository.findByLoanStatus(loan_status);
    }

    public LoanEntity findByMaxInterestRate(double max_interest_rate) {
        return loanRepository.findByMaxInterestRate(max_interest_rate);
    }

    public LoanEntity findByMaxFinancingAmount(double max_financing_amount) {
        return loanRepository.findByMaxFinancingAmount(max_financing_amount);
    }

    public LoanEntity findByCreditApplicationId(Long credit_application_id) {
        return loanRepository.findByCreditApplicationId(credit_application_id);
    }

    public List<LoanEntity> findByClientId(Long client_id) {
        return loanRepository.findByClientId(client_id);
    }

    public boolean existsByLoanId(Long loan_id) {
        return loanRepository.existsByLoanId(loan_id);
    }

    public boolean existsByClientId(Long client_id) {
        return loanRepository.existsByClientId(client_id);
    }
}

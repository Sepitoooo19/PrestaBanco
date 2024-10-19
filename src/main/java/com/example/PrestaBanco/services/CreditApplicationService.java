package com.example.PrestaBanco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PrestaBanco.repositories.CreditApplicationRepository;
import com.example.PrestaBanco.entities.CreditApplicationEntity;

import java.util.List;

@Service
public class CreditApplicationService {

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    public List<CreditApplicationEntity> findAll() {
        return creditApplicationRepository.findAll();
    }

    public List<CreditApplicationEntity> findByClientId(Long client_id) {
        return creditApplicationRepository.findByClientId(client_id);
    }

    public CreditApplicationEntity findByCreditApplicationId(Long credit_application_id) {
        return creditApplicationRepository.findByCreditApplicationId(credit_application_id);
    }

    public CreditApplicationEntity findByName(String name) {
        return creditApplicationRepository.findByName(name);
    }

    public CreditApplicationEntity findByCreditDate(String credit_date) {
        return creditApplicationRepository.findByCreditDate(credit_date);
    }

    public CreditApplicationEntity findByDocument1(boolean document_1) {
        return creditApplicationRepository.findByDocument1(document_1);
    }

    public CreditApplicationEntity findByDocument2(boolean document_2) {
        return creditApplicationRepository.findByDocument2(document_2);
    }

    public CreditApplicationEntity findByDocument3(boolean document_3) {
        return creditApplicationRepository.findByDocument3(document_3);
    }

    public CreditApplicationEntity findByDocument4(boolean document_4) {
        return creditApplicationRepository.findByDocument4(document_4);
    }

    public boolean existsByClientId(Long client_id) {
        return creditApplicationRepository.existsByClientId(client_id);
    }
}

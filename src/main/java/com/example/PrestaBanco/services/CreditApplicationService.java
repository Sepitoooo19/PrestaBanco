package com.example.PrestaBanco.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PrestaBanco.repositories.CreditApplicationRepository;
import com.example.PrestaBanco.entities.CreditApplicationEntity;
import com.example.PrestaBanco.entities.ClientEntity;
import com.example.PrestaBanco.repositories.ClientRepository;
import com.example.PrestaBanco.entities.LoanEntity;
import com.example.PrestaBanco.repositories.LoanRepository;
import java.time.LocalDate;

import java.util.List;

@Service
public class CreditApplicationService {

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LoanRepository loanRepository;

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

    public CreditApplicationEntity findByStatus(String status) {
        return creditApplicationRepository.findByStatus(status);
    }

    public boolean existsByClientId(Long client_id) {
        return creditApplicationRepository.existsByClientId(client_id);
    }

    public CreditApplicationEntity save(CreditApplicationEntity creditApplication) {
        return creditApplicationRepository.save(creditApplication);
    }

    public CreditApplicationEntity createCreditApplicationByRut(String rut, String loan_type) {
        // Buscar cliente por rut
        ClientEntity client = clientRepository.findByRut(rut);

        if (client == null) {
            throw new EntityNotFoundException("Client not found for RUT: " + rut);
        }

        // Obtener client_id del cliente encontrado
        Long client_id = client.getClient_id();

        // Crear nueva solicitud de crédito
        CreditApplicationEntity creditApplication = new CreditApplicationEntity();
        creditApplication.setClient_id(client_id);
        creditApplication.setName(loan_type);
        creditApplication.setCredit_date(LocalDate.now().toString());
        creditApplication.setDocument_1(false);
        creditApplication.setDocument_2(false);
        creditApplication.setDocument_3(false);
        creditApplication.setDocument_4(false);
        creditApplication.setStatus("PENDING");

        // Guardar la entidad en la base de datos y retornar
        return creditApplicationRepository.save(creditApplication);
    }





}

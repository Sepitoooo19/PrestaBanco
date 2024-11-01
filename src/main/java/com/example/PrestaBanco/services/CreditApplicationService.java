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


    public CreditApplicationEntity findByStatus(String status) {
        return creditApplicationRepository.findByStatus(status);
    }

    public boolean existsByClientId(Long client_id) {
        return creditApplicationRepository.existsByClientId(client_id);
    }

    public int getMonthlyLoanOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        double interest_rate = client.getInterest_rate() / 12 / 100;
        double expected_amount = client.getExpected_amount();
        int time_limit_in_months = client.getTime_limit() * 12;
        double monthly_fee = expected_amount * ((interest_rate*(Math.pow(1+interest_rate, time_limit_in_months)))/(Math.pow(1+interest_rate, time_limit_in_months)-1));
        return (int) monthly_fee;

    }



    public CreditApplicationEntity createCreditApplicationByRut(String rut, String loan_type) {
        // Buscar cliente por rut
        ClientEntity client = clientRepository.findByRut(rut);

        if (client == null) {
            throw new EntityNotFoundException("Client not found for RUT: " + rut);
        }

        // Obtener client_id del cliente encontrado
        Long client_id = client.getClient_id();

        double interest_rate = client.getInterest_rate() / 12 / 100;
        double expected_amount = client.getExpected_amount();
        int time_limit_in_months = client.getTime_limit() * 12;
        double monthly_fee = expected_amount * ((interest_rate*(Math.pow(1+interest_rate, time_limit_in_months)))/(Math.pow(1+interest_rate, time_limit_in_months)-1));
        int monthly_fee_int = (int) monthly_fee;
        // Crear nueva solicitud de crédito
        CreditApplicationEntity creditApplication = new CreditApplicationEntity();
        creditApplication.setClient_id(client_id);
        creditApplication.setName(loan_type);
        creditApplication.setCredit_date(LocalDate.now().toString());
        creditApplication.setAmount(monthly_fee_int);
        creditApplication.setStatus("PENDING");

        // Guardar la entidad en la base de datos y retornar
        return creditApplicationRepository.save(creditApplication);
    }





}

package com.example.PrestaBanco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PrestaBanco.repositories.LoanRepository;
import com.example.PrestaBanco.entities.LoanEntity;
import com.example.PrestaBanco.entities.ClientEntity;
import com.example.PrestaBanco.repositories.ClientRepository;
import com.example.PrestaBanco.entities.CreditApplicationEntity;
import com.example.PrestaBanco.repositories.CreditApplicationRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

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

    public LoanEntity save(LoanEntity loan) {
        return loanRepository.save(loan);
    }

    public List<LoanEntity> setDetailsOfCreditApplicationByRut(String rut) {

        ClientEntity client = clientRepository.findByRut(rut);
        Long client_id = client.getClient_id();
        List<LoanEntity> loans = new ArrayList<>();  // Lista para almacenar los préstamos
        List<CreditApplicationEntity> creditApplications = creditApplicationRepository.findByClientId(client_id);

        if (creditApplications.size() == 0) {
            return null;
        }

        // Iterar sobre todas las aplicaciones de crédito
        for (CreditApplicationEntity creditApplication : creditApplications) {
            String loanType = creditApplication.getName();  // Obtener el tipo de préstamo actual

            // Verificar si ya existe un préstamo del mismo tipo para el cliente
            LoanEntity existingLoan = loanRepository.findByClientIdAndLoanType(client_id, loanType);

            if (existingLoan != null) {
                // Si ya existe un préstamo de este tipo, saltarlo
                continue;
            }

            // Crear un nuevo préstamo para la aplicación de crédito actual
            LoanEntity loan = new LoanEntity();

            // Configurar los detalles del préstamo en función del tipo de solicitud de crédito
            if (loanType.equals("Primera Vivienda")) {
                loan.setLoan_type("Primera Vivienda");
                loan.setMax_time_limit(30);
                loan.setMin_interest_rate(3.5);
                loan.setMax_interest_rate(5.0);
                loan.setMax_financing_amount(80);

            } else if (loanType.equals("Segunda Vivienda")) {
                loan.setLoan_type("Segunda Vivienda");
                loan.setMax_time_limit(20);
                loan.setMin_interest_rate(4.0);
                loan.setMax_interest_rate(6.0);
                loan.setMax_financing_amount(70);

            } else if (loanType.equals("Propiedades Comerciales")) {
                loan.setLoan_type("Propiedades Comerciales");
                loan.setMax_time_limit(15);
                loan.setMin_interest_rate(5.0);
                loan.setMax_interest_rate(7.0);
                loan.setMax_financing_amount(60);

            } else if (loanType.equals("Remodelacion")) {
                loan.setLoan_type("Remodelacion");
                loan.setMax_time_limit(15);
                loan.setMin_interest_rate(4.5);
                loan.setMax_interest_rate(6.0);
                loan.setMax_financing_amount(50);
            }

            // Configurar otros valores comunes
            loan.setLoan_date(creditApplication.getCredit_date());
            loan.setLoan_status("PENDING");
            loan.setClient_id(client_id);
            loan.setCredit_application_id(creditApplication.getCredit_application_id());

            // Guardar el préstamo en la base de datos
            LoanEntity savedLoan = save(loan);

            // Agregar el préstamo guardado a la lista de préstamos
            loans.add(savedLoan);
        }

        // Retornar la lista de préstamos generados
        return loans;
    }



}

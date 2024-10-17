package com.example.PrestaBanco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PrestaBanco.repositories.ClientRepository;
import com.example.PrestaBanco.entities.ClientEntity;
import java.util.List;
import com.example.PrestaBanco.repositories.BanExecutiveRepository;
import com.example.PrestaBanco.entities.BankExecutiveEntity;
import com.example.PrestaBanco.repositories.DebtRepository;
import com.example.PrestaBanco.entities.DebtEntity;



@Service
public class BankExecutiveService {

    @Autowired
    private BanExecutiveRepository bankExecutiveRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DebtRepository debtRepository;

    public List<BankExecutiveEntity> findAll() {
        return bankExecutiveRepository.findAll();
    }

    public BankExecutiveEntity findByRut(String rut) {
        return bankExecutiveRepository.findByRut(rut);
    }

    public BankExecutiveEntity findByEmail(String email) {
        return bankExecutiveRepository.findByEmail(email);
    }

    public BankExecutiveEntity findByExecutiveId(Long executive_id) {
        return bankExecutiveRepository.findByExecutiveId(executive_id);
    }

    public BankExecutiveEntity findByName(String name) {
        return bankExecutiveRepository.findByName(name);
    }

    public BankExecutiveEntity findByPhone(String phone) {
        return bankExecutiveRepository.findByPhone(phone);
    }

    public boolean existsByRut(String rut) {
        return bankExecutiveRepository.existsByRut(rut);
    }

    public double getExpectedAmountOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getExpected_amount();

    }

    public double getInteresRateOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getInterest_rate();
    }

    public int getTimeLimitOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getTime_limit();
    }

    public double getMonthlySalaryOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getMonthly_salary();
    }

    public int getMonthlyLoanOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        double interest_rate = client.getInterest_rate() / 12 / 100;
        double expected_amount = client.getExpected_amount();
        int time_limit_in_months = client.getTime_limit() * 12;
        double monthly_fee = expected_amount * ((interest_rate*(Math.pow(1+interest_rate, time_limit_in_months)))/(Math.pow(1+interest_rate, time_limit_in_months)-1));
        return (int) monthly_fee;
        
    }

    public double getFeeIncomeRatioByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        double monthly_salary = client.getMonthly_salary();
        double monthly_fee = getMonthlyLoanOfClientByRut(rut);
        return (monthly_salary/ monthly_fee)* 100;
    }

    public double getDebtAmountByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        List<DebtEntity> debts = debtRepository.findByClientId(client.getClient_id());

        if(client.getClient_id() == null) {
            return 0;
        }

        double debt_amount = 0;
        for (DebtEntity debt : debts) {
            debt_amount += debt.getDebt_amount();
        }
        return debt_amount;

    }

}

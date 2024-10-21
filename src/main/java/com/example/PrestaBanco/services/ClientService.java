package com.example.PrestaBanco.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PrestaBanco.repositories.ClientRepository;
import com.example.PrestaBanco.entities.ClientEntity;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;





@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;





    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    public ClientEntity findByRut(String rut) {
        return clientRepository.findByRut(rut);
    }

    public ClientEntity findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public ClientEntity findByClientId(Long client_id) {
        return clientRepository.findByClientId(client_id);
    }

    public ClientEntity findByName(String name) {
        return clientRepository.findByName(name);
    }

    public ClientEntity findByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }

    public ClientEntity findByJobType(String job_type) {
        return clientRepository.findByJobType(job_type);
    }

    public ClientEntity findByTypeLoan(String type_loan) {
        return clientRepository.findByTypeLoan(type_loan);
    }

    public ClientEntity findByIndependentActivity(boolean independent_activity) {
        return clientRepository.findByIndependentActivity(independent_activity);
    }

    public ClientEntity findByAge(int age) {
        return clientRepository.findByAge(age);
    }

    public ClientEntity findByMonthlySalary(double monthly_salary) {
        return clientRepository.findByMonthlySalary(monthly_salary);
    }

    public ClientEntity findByPersonalSavings(Double personal_savings) {
        return clientRepository.findByPersonalSavings(personal_savings);
    }

    public ClientEntity findByExpectedAmount(double expected_amount) {
        return clientRepository.findByExpectedAmount(expected_amount);
    }

    public ClientEntity findByTimeLimit(int time_limit) {
        return clientRepository.findByTimeLimit(time_limit);
    }

    public ClientEntity findByInterestRate(double interest_rate) {
        return clientRepository.findByInterestRate(interest_rate);
    }

    public ClientEntity save(ClientEntity client) {
        clientRepository.save(client);
        return client;
    }

    public void deleteById(Long client_id) {
        clientRepository.deleteById(client_id);
    }

    public void delete(ClientEntity client) {
        clientRepository.delete(client);
    }

    public void deleteAll() {
        clientRepository.deleteAll();
    }

    public boolean existsById(Long client_id) {
        return clientRepository.existsById(client_id);
    }

    public boolean existsByRut(String rut) {
        return clientRepository.existsByRut(rut);
    }


}

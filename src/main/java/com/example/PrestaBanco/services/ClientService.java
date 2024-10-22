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
        if(rut == null){
            return null;
        }
        return clientRepository.findByRut(rut.trim().toLowerCase());
    }

    public ClientEntity findByEmail(String email) {
        if(email == null){
            return null;
        }
        return clientRepository.findByEmail(email.trim().toLowerCase());
    }

    public ClientEntity findByClientId(Long client_id) {
        return clientRepository.findByClientId(client_id);
    }

    public ClientEntity findByName(String name) {
        if (name == null) {
            return null;
        }
        return clientRepository.findByName(name.trim().toLowerCase());
    }
    public ClientEntity findByPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return null;
        }
        return clientRepository.findByPhone(phone.trim());
    }

    public ClientEntity findByJobType(String job_type) {
        if (job_type == null || job_type.trim().isEmpty()) {
            return null;
        }
        return clientRepository.findByJobType(job_type.trim());
    }
    public ClientEntity findByTypeLoan(String type_loan) {
        if (type_loan == null || type_loan.trim().isEmpty()) {
            return null;
        }
        return clientRepository.findByTypeLoan(type_loan.trim());
    }

    public ClientEntity findByIndependentActivity(boolean independent_activity) {
        return clientRepository.findByIndependentActivity(independent_activity);
    }

    public ClientEntity findByAge(int age) {
        if (age < 0) {
            return null; // Retornar null si la edad es negativa
        }
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

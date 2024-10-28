package com.example.PrestaBanco.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PrestaBanco.repositories.ClientRepository;
import com.example.PrestaBanco.entities.ClientEntity;
import com.example.PrestaBanco.repositories.DocumentRepository;
import com.example.PrestaBanco.entities.DocumentEntity;


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
        if (monthly_salary < 0) {
            return null; // Retornar null si el salario es negativo
        }
        return clientRepository.findByMonthlySalary(monthly_salary);
    }

    public ClientEntity findByPersonalSavings(Double personal_savings) {
        if (personal_savings != null && personal_savings < 0) {
            return null; // Retornar null si los ahorros son negativos
        }
        return clientRepository.findByPersonalSavings(personal_savings);
    }


    public ClientEntity findByExpectedAmount(double expected_amount) {
        if (expected_amount < 0) {
            return null; // Retornar null si el monto esperado es negativo
        }
        return clientRepository.findByExpectedAmount(expected_amount);
    }

    public ClientEntity findByTimeLimit(int time_limit) {
        if (time_limit < 0) {
            return null; // Retornar null si el límite de tiempo es negativo
        }
        return clientRepository.findByTimeLimit(time_limit);
    }

    public ClientEntity findByInterestRate(double interest_rate) {
        if (interest_rate < 0) {
            return null; // Retornar null si la tasa de interés es negativa
        }
        return clientRepository.findByInterestRate(interest_rate);
    }

    public ClientEntity save(ClientEntity client) {
        clientRepository.save(client);
        return client;
    }

    public void deleteById(Long client_id) {
        if (client_id == null || client_id <= 0) {
            // No hacer nada si el ID es null, 0 o negativo
            return;
        }
        clientRepository.deleteById(client_id);
    }
    public void delete(ClientEntity client) {
        if (client == null || client.getClient_id() == null) {
            // No hacer nada si el cliente o su ID es nulo
            return;
        }
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

    public ClientEntity updateClientByRut(String rut, ClientEntity updatedClient) {
        ClientEntity existingClient = clientRepository.findByRut(rut);
        if (existingClient == null) {
            return null; // Si no existe un cliente con ese RUT, retornar null
        }

        // Actualiza los campos deseados del cliente existente
        existingClient.setName(updatedClient.getName());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPhone(updatedClient.getPhone());
        existingClient.setAge(updatedClient.getAge());
        existingClient.setMonthly_salary(updatedClient.getMonthly_salary());
        existingClient.setPersonal_savings(updatedClient.getPersonal_savings());
        existingClient.setJob_type(updatedClient.getJob_type());
        existingClient.setExpected_amount(updatedClient.getExpected_amount());
        existingClient.setTime_limit(updatedClient.getTime_limit());
        existingClient.setInterest_rate(updatedClient.getInterest_rate());
        existingClient.setType_loan(updatedClient.getType_loan());
        existingClient.setIndependent_activity(updatedClient.isIndependent_activity());
        existingClient.setJob_seniority(updatedClient.getJob_seniority());
        existingClient.setActual_job(updatedClient.getActual_job());

        return clientRepository.save(existingClient);
    }





}

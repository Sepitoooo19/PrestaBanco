package com.example.PrestaBanco.repositories;

import com.example.PrestaBanco.entities.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// Importa AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // Levanta solo el contexto de JPA para pruebas de repositorio
@AutoConfigureTestDatabase(replace = Replace.NONE)  // Evita el reemplazo del DataSource
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;  // Repositorio a probar

    @Test
    public void whenFindByRut_thenReturnClient() {
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        clientRepository.save(client);
        ClientEntity foundClient = clientRepository.findByRut("12345678-9");
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getRut()).isEqualTo("12345678-9");
    }

    @Test
    public void whenFindByRutInvalid_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByRut("00000000-0");
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByRutCaseInsensitive_thenReturnClient() {
        ClientEntity client = new ClientEntity(null, "James Smith", "98765432-1", "james@example.com", "987654321", 28, 3000.0, 12000.0, "Architect", 60000.0, 24, 3.5, "Mortgage", true, 10, "Lead Architect", 1, 0);
        clientRepository.save(client);
        ClientEntity foundClient = clientRepository.findByRut("98765432-1");
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getRut()).isEqualTo("98765432-1");
    }

    @Test
    public void whenFindByRutNull_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByRut(null);
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByRutWithSpecialCharacters_thenReturnClient() {
        ClientEntity client = new ClientEntity(null, "Carlos Sánchez", "11122233-K", "carlos@example.com", "111222333", 35, 4500.0, 18000.0, "Engineer", 55000.0, 36, 2.2, "Auto Loan", false, 6, "Project Manager", 2, 0);
        clientRepository.save(client);
        ClientEntity foundClient = clientRepository.findByRut("11122233-K");
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getRut()).isEqualTo("11122233-K");
    }

    // 2. Tests for findByEmail
    @Test
    public void whenFindByEmail_thenReturnClient() {
        ClientEntity client = new ClientEntity(null, "Jane Doe", "98765432-1", "jane@example.com", "987654321", 28, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1);
        clientRepository.save(client);
        ClientEntity foundClient = clientRepository.findByEmail("jane@example.com");
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getEmail()).isEqualTo("jane@example.com");
    }

    @Test
    public void whenFindByEmailInvalid_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByEmail("invalid@example.com");
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByEmailCaseInsensitive_thenReturnClient() {
        ClientEntity client = new ClientEntity(null, "David Brown", "22334455-2", "david@example.com", "223344555", 29, 3500.0, 16000.0, "Lawyer", 65000.0, 48, 3.1, "Mortgage", false, 9, "Associate", 3, 0);
        clientRepository.save(client);
        ClientEntity foundClient = clientRepository.findByEmail("david@EXAMPLE.com");
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getEmail()).isEqualTo("david@example.com");
    }

    @Test
    public void whenFindByEmailNull_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByEmail(null);
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByEmailSpecialCharacters_thenReturnClient() {
        ClientEntity client = new ClientEntity(null, "Emily Jones", "33445566-4", "emily.jones@example.com", "334455666", 26, 4000.0, 14000.0, "HR Manager", 52000.0, 30, 2.9, "Business Loan", false, 7, "HR Manager", 4, 0);
        clientRepository.save(client);
        ClientEntity foundClient = clientRepository.findByEmail("emily.jones@example.com");
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getEmail()).isEqualTo("emily.jones@example.com");
    }

    // 3. Tests for findByClientId
    @Test
    public void whenFindByClientId_thenReturnClient() {
        ClientEntity client = new ClientEntity(null, "Mark Lee", "55667788-5", "mark@example.com", "556677888", 32, 5000.0, 22000.0, "Consultant", 70000.0, 36, 3.5, "Personal Loan", true, 10, "Consultant", 5, 0);
        clientRepository.save(client);
        ClientEntity foundClient = clientRepository.findByClientId(client.getClient_id());
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getClient_id()).isEqualTo(client.getClient_id());
    }

    @Test
    public void whenFindByClientIdInvalid_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByClientId(999L);
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByClientIdNull_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByClientId(null);
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByClientIdZero_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByClientId(0L);
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByClientIdNegative_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByClientId(-1L);
        assertThat(foundClient).isNull();
    }

    // 4. Tests for findByPhone
    @Test
    public void whenFindByPhone_thenReturnClient() {
        ClientEntity client = new ClientEntity(null, "Sophia Green", "22334455-1", "sophia@example.com", "111222333", 29, 4500.0, 15000.0, "Designer", 62000.0, 24, 2.8, "Mortgage", true, 6, "Designer", 1, 0);
        clientRepository.save(client);
        ClientEntity foundClient = clientRepository.findByPhone("111222333");
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getPhone()).isEqualTo("111222333");
    }

    @Test
    public void whenFindByPhoneInvalid_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByPhone("999999999");
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByPhoneNull_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByPhone(null);
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByPhoneEmpty_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByPhone("");
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByPhoneSpecialCharacters_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByPhone("abc123");
        assertThat(foundClient).isNull();
    }

    // 5. Tests for findByJobType
    @Test
    public void whenFindByJobType_thenReturnClient() {
        ClientEntity client = new ClientEntity(null, "Daniel Johnson", "22334455-2", "daniel@example.com", "223344555", 29, 5500.0, 17000.0, "Engineer", 75000.0, 24, 3.1, "Personal Loan", false, 9, "Engineer", 3, 0);
        clientRepository.save(client);
        ClientEntity foundClient = clientRepository.findByJobType("Engineer");
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getJob_type()).isEqualTo("Engineer");
    }

    @Test
    public void whenFindByJobTypeInvalid_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByJobType("Invalid Job");
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByJobTypeNull_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByJobType(null);
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByJobTypeEmpty_thenReturnNull() {
        ClientEntity foundClient = clientRepository.findByJobType("");
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByJobTypeCaseInsensitive_thenReturnClient() {
        ClientEntity client = new ClientEntity(null, "Emma White", "33445566-3", "emma@example.com", "334455666", 28, 6000.0, 20000.0, "Scientist", 80000.0, 36, 2.9, "Personal Loan", true, 8, "Scientist", 4, 0);
        clientRepository.save(client);
        ClientEntity foundClient = clientRepository.findByJobType("scientist");
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getJob_type()).isEqualTo("Scientist");
    }


}
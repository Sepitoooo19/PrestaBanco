package com.example.PrestaBanco.services;

import com.example.PrestaBanco.entities.ClientEntity;
import com.example.PrestaBanco.repositories.ClientRepository;
import com.example.PrestaBanco.repositories.CreditApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.PrestaBanco.entities.CreditApplicationEntity;
import com.example.PrestaBanco.entities.LoanEntity;
import com.example.PrestaBanco.repositories.LoanRepository;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CreditApplicationRepository creditApplicationRepository;


    @Mock
    private LoanRepository loanRepository;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 1. Prueba para devolver una lista con clientes
    @Test
    public void whenFindAll_thenReturnClients() {
        // given
        List<ClientEntity> clients = Arrays.asList(
                new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0),
                new ClientEntity(null, "Jane Doe", "98765432-1", "jane@example.com", "987654321", 28, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1)
        );
        given(clientRepository.findAll()).willReturn(clients);

        // when
        List<ClientEntity> foundClients = clientService.findAll();

        // then
        assertThat(foundClients).isNotEmpty();
        assertThat(foundClients.size()).isEqualTo(2);
    }

    // 2. Prueba cuando no hay clientes
    @Test
    public void whenFindAll_thenReturnEmptyList() {
        // given
        given(clientRepository.findAll()).willReturn(Collections.emptyList());

        // when
        List<ClientEntity> foundClients = clientService.findAll();

        // then
        assertThat(foundClients).isEmpty();
    }

    // 3. Prueba para verificar que el método del repositorio se llama una vez
    @Test
    public void whenFindAll_thenRepositoryMethodCalledOnce() {
        // given
        List<ClientEntity> clients = Arrays.asList(
                new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0)
        );
        given(clientRepository.findAll()).willReturn(clients);

        // when
        clientService.findAll();

        // then
        verify(clientRepository, times(1)).findAll();
    }

    // 4. Prueba para asegurar que se devuelve una lista de un solo cliente
    @Test
    public void whenFindAll_thenReturnSingleClient() {
        // given
        List<ClientEntity> clients = Arrays.asList(
                new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0)
        );
        given(clientRepository.findAll()).willReturn(clients);

        // when
        List<ClientEntity> foundClients = clientService.findAll();

        // then
        assertThat(foundClients.size()).isEqualTo(1);
        assertThat(foundClients.get(0).getName()).isEqualTo("John Doe");
    }

    // 5. Prueba con clientes con diferentes edades
    @Test
    public void whenFindAll_thenClientsHaveDifferentAges() {
        // given
        List<ClientEntity> clients = Arrays.asList(
                new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0),
                new ClientEntity(null, "Jane Smith", "98765432-1", "jane@example.com", "987654321", 25, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1)
        );
        given(clientRepository.findAll()).willReturn(clients);

        // when
        List<ClientEntity> foundClients = clientService.findAll();

        // then
        assertThat(foundClients.get(0).getAge()).isNotEqualTo(foundClients.get(1).getAge());
    }

    // 6. Prueba para verificar que los clientes tienen diferentes correos electrónicos
    @Test
    public void whenFindAll_thenClientsHaveDifferentEmails() {
        // given
        List<ClientEntity> clients = Arrays.asList(
                new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0),
                new ClientEntity(null, "Jane Smith", "98765432-1", "jane@example.com", "987654321", 25, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1)
        );
        given(clientRepository.findAll()).willReturn(clients);

        // when
        List<ClientEntity> foundClients = clientService.findAll();

        // then
        assertThat(foundClients.get(0).getEmail()).isNotEqualTo(foundClients.get(1).getEmail());
    }

    // 7. Prueba para asegurar que se devuelven clientes con diferentes montos de ingresos
    @Test
    public void whenFindAll_thenClientsHaveDifferentIncome() {
        // given
        List<ClientEntity> clients = Arrays.asList(
                new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0),
                new ClientEntity(null, "Jane Smith", "98765432-1", "jane@example.com", "987654321", 25, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1)
        );
        given(clientRepository.findAll()).willReturn(clients);

        // when
        List<ClientEntity> foundClients = clientService.findAll();

        // then
        assertThat(foundClients.get(0).getMonthly_salary()).isNotEqualTo(foundClients.get(1).getMonthly_salary());
    }
    // 8. Prueba para asegurar que se devuelve una lista de clientes con diferentes ocupaciones
    @Test
    public void whenFindAll_thenClientsHaveDifferentOccupations() {
        // given
        List<ClientEntity> clients = Arrays.asList(
                new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0),
                new ClientEntity(null, "Jane Smith", "98765432-1", "jane@example.com", "987654321", 25, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1)
        );
        given(clientRepository.findAll()).willReturn(clients);

        // when
        List<ClientEntity> foundClients = clientService.findAll();

        // then
        assertThat(foundClients.get(0).getJob_type()).isNotEqualTo(foundClients.get(1).getJob_type());
    }

    // 9. Prueba cuando el nombre del cliente contiene un número
    @Test
    public void whenFindAll_thenClientNameContainsNumber() {
        // given
        List<ClientEntity> clients = Arrays.asList(
                new ClientEntity(null, "John Doe2", "12345678-9", "john2@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0)
        );
        given(clientRepository.findAll()).willReturn(clients);

        // when
        List<ClientEntity> foundClients = clientService.findAll();

        // then
        assertThat(foundClients.get(0).getName()).contains("2");
    }

    // 1. Prueba cuando el cliente existe
    @Test
    public void whenFindByRut_thenReturnClient() {
        // given
        String rut = "12345678-9";
        ClientEntity client = new ClientEntity(null, "John Doe", rut, "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByRut(rut)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByRut(rut);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getRut()).isEqualTo(rut);
    }

    // 2. Prueba cuando el cliente no existe
    @Test
    public void whenFindByRut_thenReturnNull() {
        // given
        String rut = "12345678-9";
        given(clientRepository.findByRut(rut)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByRut(rut);

        // then
        assertThat(foundClient).isNull();
    }

    // 3. Prueba cuando el RUT es nulo
    @Test
    public void whenFindByRutWithNullRut_thenReturnNull() {
        // given
        given(clientRepository.findByRut(null)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByRut(null);

        // then
        assertThat(foundClient).isNull();
    }

    // 4. Prueba cuando el RUT tiene un formato incorrecto
    @Test
    public void whenFindByRutWithInvalidFormat_thenReturnNull() {
        // given
        String invalidRut = "invalid-rut";
        given(clientRepository.findByRut(invalidRut)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByRut(invalidRut);

        // then
        assertThat(foundClient).isNull();
    }

    // 5. Prueba que se llame al método del repositorio una vez
    @Test
    public void whenFindByRut_thenRepositoryMethodCalledOnce() {
        // given
        String rut = "12345678-9";
        ClientEntity client = new ClientEntity(null, "John Doe", rut, "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByRut(rut)).willReturn(client);

        // when
        clientService.findByRut(rut);

        // then
        verify(clientRepository, times(1)).findByRut(rut);
    }

    // 6. Prueba cuando se busca un cliente por un RUT que contiene espacios
    @Test
    public void whenFindByRutWithSpaces_thenReturnClient() {
        // given
        String rutWithSpaces = " 12345678-9 ";
        String rutTrimmed = "12345678-9";
        ClientEntity client = new ClientEntity(null, "John Doe", rutTrimmed, "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByRut(rutTrimmed)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByRut(rutWithSpaces.trim());

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getRut()).isEqualTo(rutTrimmed);
    }

    // 7. Prueba cuando se busca un cliente por un RUT en minúsculas
    @Test
    public void whenFindByRutLowerCase_thenReturnClient() {
        // given
        String rutLowerCase = "12345678-9";
        ClientEntity client = new ClientEntity(null, "John Doe", rutLowerCase, "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByRut(rutLowerCase)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByRut(rutLowerCase);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getRut()).isEqualTo(rutLowerCase);
    }
    // 1. Prueba cuando el cliente existe por email
    @Test
    public void whenFindByEmail_thenReturnClient() {
        // given
        String email = "john@example.com";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", email, "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByEmail(email)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByEmail(email);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getEmail()).isEqualTo(email);
    }

    // 2. Prueba cuando el cliente no existe
    @Test
    public void whenFindByEmail_thenReturnNull() {
        // given
        String email = "nonexistent@example.com";
        given(clientRepository.findByEmail(email)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByEmail(email);

        // then
        assertThat(foundClient).isNull();
    }

    // 3. Prueba cuando el email es nulo
    @Test
    public void whenFindByEmailWithNullEmail_thenReturnNull() {
        // given
        given(clientRepository.findByEmail(null)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByEmail(null);

        // then
        assertThat(foundClient).isNull();
    }

    // 4. Prueba cuando el email tiene formato incorrecto
    @Test
    public void whenFindByEmailWithInvalidFormat_thenReturnNull() {
        // given
        String invalidEmail = "invalid-email";
        given(clientRepository.findByEmail(invalidEmail)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByEmail(invalidEmail);

        // then
        assertThat(foundClient).isNull();
    }

    // 5. Prueba que el método del repositorio sea llamado una vez
    @Test
    public void whenFindByEmail_thenRepositoryMethodCalledOnce() {
        // given
        String email = "john@example.com";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", email, "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByEmail(email)).willReturn(client);

        // when
        clientService.findByEmail(email);

        // then
        verify(clientRepository, times(1)).findByEmail(email);
    }

    // 6. Prueba cuando el email contiene mayúsculas
    @Test
    public void whenFindByEmailWithUpperCase_thenReturnClient() {
        // given
        String emailWithUpperCase = "JOHN@EXAMPLE.COM";
        String emailLowerCase = "john@example.com";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", emailLowerCase, "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByEmail(emailLowerCase)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByEmail(emailWithUpperCase.toLowerCase());

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getEmail()).isEqualTo(emailLowerCase);
    }

    // 7. Prueba cuando el email contiene espacios en blanco
    @Test
    public void whenFindByEmailWithSpaces_thenReturnClient() {
        // given
        String emailWithSpaces = " john@example.com ";
        String emailTrimmed = "john@example.com";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", emailTrimmed, "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByEmail(emailTrimmed)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByEmail(emailWithSpaces.trim());

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getEmail()).isEqualTo(emailTrimmed);
    }

    // 1. Prueba cuando el cliente existe por ID
    @Test
    public void whenFindByClientId_thenReturnClient() {
        // given
        Long clientId = 1L;
        ClientEntity client = new ClientEntity(clientId, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByClientId(clientId)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByClientId(clientId);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getClient_id()).isEqualTo(clientId);
    }

    // 2. Prueba cuando el cliente no existe
    @Test
    public void whenFindByClientId_thenReturnNull() {
        // given
        Long clientId = 1L;
        given(clientRepository.findByClientId(clientId)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByClientId(clientId);

        // then
        assertThat(foundClient).isNull();
    }

    // 3. Prueba cuando el ID es nulo
    @Test
    public void whenFindByClientIdWithNullId_thenReturnNull() {
        // given
        given(clientRepository.findByClientId(null)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByClientId(null);

        // then
        assertThat(foundClient).isNull();
    }

    // 4. Prueba cuando el ID es negativo
    @Test
    public void whenFindByClientIdWithNegativeId_thenReturnNull() {
        // given
        Long negativeId = -1L;
        given(clientRepository.findByClientId(negativeId)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByClientId(negativeId);

        // then
        assertThat(foundClient).isNull();
    }

    // 5. Prueba que el método del repositorio sea llamado una vez
    @Test
    public void whenFindByClientId_thenRepositoryMethodCalledOnce() {
        // given
        Long clientId = 1L;
        ClientEntity client = new ClientEntity(clientId, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByClientId(clientId)).willReturn(client);

        // when
        clientService.findByClientId(clientId);

        // then
        verify(clientRepository, times(1)).findByClientId(clientId);
    }

    // 6. Prueba cuando el ID es grande
    @Test
    public void whenFindByClientIdWithLargeId_thenReturnClient() {
        // given
        Long largeClientId = 100000000L;
        ClientEntity client = new ClientEntity(largeClientId, "Jane Smith", "98765432-1", "jane@example.com", "987654321", 28, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1);
        given(clientRepository.findByClientId(largeClientId)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByClientId(largeClientId);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getClient_id()).isEqualTo(largeClientId);
    }

    // 7. Prueba cuando se busca un cliente con ID 0
    @Test
    public void whenFindByClientIdWithZeroId_thenReturnNull() {
        // given
        Long zeroId = 0L;
        given(clientRepository.findByClientId(zeroId)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByClientId(zeroId);

        // then
        assertThat(foundClient).isNull();
    }

    // 1. Prueba cuando el cliente existe por nombre
    @Test
    public void whenFindByName_thenReturnClient() {
        // given
        String name = "John Doe";
        String normalized = name.toLowerCase();
        ClientEntity client = new ClientEntity(null, name, "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByName(normalized)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByName(name);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getName()).isEqualTo(name);
    }

    // 2. Prueba cuando el cliente no existe
    @Test
    public void whenFindByName_thenReturnNull() {
        // given
        String name = "Nonexistent Name";
        given(clientRepository.findByName(name)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByName(name);

        // then
        assertThat(foundClient).isNull();
    }

    // 3. Prueba cuando el nombre es nulo
    @Test
    public void whenFindByNameWithNullName_thenReturnNull() {
        // given
        given(clientRepository.findByName(null)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByName(null);

        // then
        assertThat(foundClient).isNull();
    }

    // 4. Prueba cuando el nombre está vacío
    @Test
    public void whenFindByNameWithEmptyName_thenReturnNull() {
        // given
        String emptyName = "";
        given(clientRepository.findByName(emptyName)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByName(emptyName);

        // then
        assertThat(foundClient).isNull();
    }

    // 5. Prueba que el método del repositorio sea llamado una vez
    @Test
    public void whenFindByName_thenRepositoryMethodCalledOnce() {
        // given
        String name = "John Doe";
        String normalized = name.toLowerCase(); // Se debe comparar con el valor esperado en el repositorio
        ClientEntity client = new ClientEntity(null, name, "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByName(normalized)).willReturn(client);

        // when
        clientService.findByName(name);

        // then
        verify(clientRepository, times(1)).findByName(normalized);
    }

    // 6. Prueba cuando el nombre contiene espacios en blanco
    @Test
    public void whenFindByNameWithSpaces_thenReturnClient() {
        // given
        String nameWithSpaces = " John Doe ";
        String trimmedName = "John Doe";
        ClientEntity client = new ClientEntity(null, trimmedName, "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByName(trimmedName.toLowerCase())).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByName(nameWithSpaces);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getName()).isEqualTo(trimmedName);
    }

    // 7. Prueba cuando el nombre contiene mayúsculas
    @Test
    public void whenFindByNameWithUpperCase_thenReturnClient() {
        // given
        String nameUpperCase = "JOHN DOE";
        String nameLowerCase = "john doe"; // Cambia a minúsculas si el servicio lo convierte
        ClientEntity client = new ClientEntity(null, nameLowerCase, "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByName(nameLowerCase)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByName(nameUpperCase);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getName()).isEqualTo(nameLowerCase);
    }

    //1. Prueba cuando el teléfono es válido
    @Test
    public void whenFindByPhone_thenReturnClient() {
        // given
        String phone = "123456789";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", phone, 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByPhone(phone)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByPhone(phone);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getPhone()).isEqualTo(phone);
    }

    //2. Prueba cuando el telefono es nulo
    @Test
    public void whenFindByPhoneWithNullPhone_thenReturnNull() {
        // given
        given(clientRepository.findByPhone(null)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByPhone(null);

        // then
        assertThat(foundClient).isNull();
    }

    //3. Prueba cuando el teléfono es vacío
    @Test
    public void whenFindByPhoneWithEmptyPhone_thenReturnNull() {
        // given
        String emptyPhone = "";
        given(clientRepository.findByPhone(emptyPhone)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByPhone(emptyPhone);

        // then
        assertThat(foundClient).isNull();
    }

    //4. Prueba cuando el telefono tiene espacios en blanco
    @Test
    public void whenFindByPhoneWithSpaces_thenReturnClient() {
        // given
        String phoneWithSpaces = " 123456789 ";
        String trimmedPhone = "123456789";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", trimmedPhone, 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByPhone(trimmedPhone)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByPhone(phoneWithSpaces.trim());

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getPhone()).isEqualTo(trimmedPhone);
    }

    // 5. Prueba que el numero de telefono contiene caracteres no numericos
    @Test
    public void whenFindByPhoneWithNonNumericCharacters_thenReturnNull() {
        // given
        String phoneWithChars = "123-456-789";
        given(clientRepository.findByPhone(phoneWithChars)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByPhone(phoneWithChars);

        // then
        assertThat(foundClient).isNull();
    }

    //6. Prueba que no existe un cliente con el telefono dado
    @Test
    public void whenFindByPhone_thenReturnNullIfNotFound() {
        // given
        String phone = "987654321";
        given(clientRepository.findByPhone(phone)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByPhone(phone);

        // then
        assertThat(foundClient).isNull();
    }

    //7. Prueba que el método del repositorio sea llamado una vez
    @Test
    public void whenFindByPhone_thenRepositoryMethodCalledOnce() {
        // given
        String phone = "123456789";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", phone, 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByPhone(phone)).willReturn(client);

        // when
        clientService.findByPhone(phone);

        // then
        verify(clientRepository, times(1)).findByPhone(phone);
    }

    // 1. Prueba cuando el tipo de trabajo es válido
    @Test
    public void whenFindByJobType_thenReturnClient() {
        // given
        String jobType = "Software Engineer";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, jobType, 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByJobType(jobType)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByJobType(jobType);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getJob_type()).isEqualTo(jobType);
    }

    // 2. Prueba cuando el tipo de trabajo es null
    @Test
    public void whenFindByJobTypeWithNullJobType_thenReturnNull() {
        // given
        given(clientRepository.findByJobType(null)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByJobType(null);

        // then
        assertThat(foundClient).isNull();
    }

    // 3. Prueba cuando el tipo de trabajo es una cadena vacía
    @Test
    public void whenFindByJobTypeWithEmptyJobType_thenReturnNull() {
        // given
        String emptyJobType = "";
        given(clientRepository.findByJobType(emptyJobType)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByJobType(emptyJobType);

        // then
        assertThat(foundClient).isNull();
    }

    // 4. Prueba cuando el tipo de trabajo tiene espacios adicionales
    @Test
    public void whenFindByJobTypeWithSpaces_thenReturnClient() {
        // given
        String jobTypeWithSpaces = "  Software Engineer  ";
        String trimmedJobType = "Software Engineer";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, trimmedJobType, 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByJobType(trimmedJobType)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByJobType(jobTypeWithSpaces);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getJob_type()).isEqualTo(trimmedJobType);
    }

    // 5. Prueba cuando el cliente no existe para el tipo de trabajo dado
    @Test
    public void whenFindByJobType_thenReturnNullIfNotFound() {
        // given
        String jobType = "Designer";
        given(clientRepository.findByJobType(jobType)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByJobType(jobType);

        // then
        assertThat(foundClient).isNull();
    }

    // 6. Verificar que el repositorio es llamado una vez
    @Test
    public void whenFindByJobType_thenRepositoryMethodCalledOnce() {
        // given
        String jobType = "Software Engineer";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, jobType, 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByJobType(jobType)).willReturn(client);

        // when
        clientService.findByJobType(jobType);

        // then
        verify(clientRepository, times(1)).findByJobType(jobType);
    }

    // 7. Prueba cuando el tipo de trabajo contiene caracteres no válidos
    @Test
    public void whenFindByJobTypeWithInvalidCharacters_thenReturnNull() {
        // given
        String jobTypeWithInvalidChars = "Software@Engineer";
        given(clientRepository.findByJobType(jobTypeWithInvalidChars)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByJobType(jobTypeWithInvalidChars);

        // then
        assertThat(foundClient).isNull();
    }

    // 1. Prueba cuando el tipo de préstamo es válido
    @Test
    public void whenFindByTypeLoan_thenReturnClient() {
        // given
        String loanType = "Personal Loan";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, loanType, false, 5, "Developer", 1, 0);
        given(clientRepository.findByTypeLoan(loanType)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByTypeLoan(loanType);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getType_loan()).isEqualTo(loanType);
    }

    // 2. Prueba cuando el tipo de préstamo es null
    @Test
    public void whenFindByTypeLoanWithNullLoanType_thenReturnNull() {
        // given
        given(clientRepository.findByTypeLoan(null)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByTypeLoan(null);

        // then
        assertThat(foundClient).isNull();
    }

    // 3. Prueba cuando el tipo de préstamo es una cadena vacía
    @Test
    public void whenFindByTypeLoanWithEmptyLoanType_thenReturnNull() {
        // given
        String emptyLoanType = "";
        given(clientRepository.findByTypeLoan(emptyLoanType)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByTypeLoan(emptyLoanType);

        // then
        assertThat(foundClient).isNull();
    }

    // 4. Prueba cuando el tipo de préstamo tiene espacios adicionales
    @Test
    public void whenFindByTypeLoanWithSpaces_thenReturnClient() {
        // given
        String loanTypeWithSpaces = "  Personal Loan  ";
        String trimmedLoanType = "Personal Loan";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, trimmedLoanType, false, 5, "Developer", 1, 0);
        given(clientRepository.findByTypeLoan(trimmedLoanType)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByTypeLoan(loanTypeWithSpaces);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getType_loan()).isEqualTo(trimmedLoanType);
    }

    // 5. Prueba cuando el cliente no existe para el tipo de préstamo dado
    @Test
    public void whenFindByTypeLoan_thenReturnNullIfNotFound() {
        // given
        String loanType = "Mortgage";
        given(clientRepository.findByTypeLoan(loanType)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByTypeLoan(loanType);

        // then
        assertThat(foundClient).isNull();
    }

    // 6. Verificar que el repositorio es llamado una vez
    @Test
    public void whenFindByTypeLoan_thenRepositoryMethodCalledOnce() {
        // given
        String loanType = "Personal Loan";
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, loanType, false, 5, "Developer", 1, 0);
        given(clientRepository.findByTypeLoan(loanType)).willReturn(client);

        // when
        clientService.findByTypeLoan(loanType);

        // then
        verify(clientRepository, times(1)).findByTypeLoan(loanType);
    }

    // 7. Prueba cuando el tipo de préstamo contiene caracteres no válidos
    @Test
    public void whenFindByTypeLoanWithInvalidCharacters_thenReturnNull() {
        // given
        String loanTypeWithInvalidChars = "Personal@Loan";
        given(clientRepository.findByTypeLoan(loanTypeWithInvalidChars)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByTypeLoan(loanTypeWithInvalidChars);

        // then
        assertThat(foundClient).isNull();
    }

    // 1. Prueba cuando la actividad independiente es verdadera
    @Test
    public void whenFindByIndependentActivityTrue_thenReturnClient() {
        // given
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByIndependentActivity(true)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByIndependentActivity(true);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getName()).isEqualTo("John Doe");
    }

    // 2. Prueba cuando la actividad independiente es falsa
    @Test
    public void whenFindByIndependentActivityFalse_thenReturnClient() {
        // given
        ClientEntity client = new ClientEntity(null, "Jane Doe", "98765432-1", "jane@example.com", "987654321", 28, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1);
        given(clientRepository.findByIndependentActivity(false)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByIndependentActivity(false);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getName()).isEqualTo("Jane Doe");
    }

    // 3. Prueba cuando no hay cliente con actividad independiente verdadera
    @Test
    public void whenFindByIndependentActivityTrue_thenReturnNullIfNotFound() {
        // given
        given(clientRepository.findByIndependentActivity(true)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByIndependentActivity(true);

        // then
        assertThat(foundClient).isNull();
    }

    // 4. Prueba cuando no hay cliente con actividad independiente falsa
    @Test
    public void whenFindByIndependentActivityFalse_thenReturnNullIfNotFound() {
        // given
        given(clientRepository.findByIndependentActivity(false)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByIndependentActivity(false);

        // then
        assertThat(foundClient).isNull();
    }

    // 5. Verificar que el repositorio es llamado una vez con valor verdadero
    @Test
    public void whenFindByIndependentActivityTrue_thenRepositoryMethodCalledOnce() {
        // given
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByIndependentActivity(true)).willReturn(client);

        // when
        clientService.findByIndependentActivity(true);

        // then
        verify(clientRepository, times(1)).findByIndependentActivity(true);
    }

    // 6. Verificar que el repositorio es llamado una vez con valor falso
    @Test
    public void whenFindByIndependentActivityFalse_thenRepositoryMethodCalledOnce() {
        // given
        ClientEntity client = new ClientEntity(null, "Jane Doe", "98765432-1", "jane@example.com", "987654321", 28, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1);
        given(clientRepository.findByIndependentActivity(false)).willReturn(client);

        // when
        clientService.findByIndependentActivity(false);

        // then
        verify(clientRepository, times(1)).findByIndependentActivity(false);
    }

    // 7. Prueba que el método funcione con diferentes instancias de cliente
    @Test
    public void whenFindByIndependentActivity_thenReturnCorrectClientBasedOnActivity() {
        // given
        ClientEntity independentClient = new ClientEntity(null, "Alice", "12345678-0", "alice@example.com", "123456780", 35, 2500.0, 12000.0, "Consultant", 55000.0, 10, 3.5, "Business Loan", false, 3, "Manager", 3, 0);
        given(clientRepository.findByIndependentActivity(true)).willReturn(independentClient);

        // when
        ClientEntity foundClient = clientService.findByIndependentActivity(true);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getName()).isEqualTo("Alice");
    }
    // 1. Prueba cuando se busca un cliente con edad válida
    @Test
    public void whenFindByAge_thenReturnClient() {
        // given
        int age = 30;
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", age, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByAge(age)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByAge(age);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getAge()).isEqualTo(age);
    }

    // 2. Prueba cuando no hay cliente con la edad especificada
    @Test
    public void whenFindByAge_thenReturnNullIfNotFound() {
        // given
        int age = 40;
        given(clientRepository.findByAge(age)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByAge(age);

        // then
        assertThat(foundClient).isNull();
    }

    // 3. Prueba cuando la edad es negativa
    @Test
    public void whenFindByNegativeAge_thenReturnNull() {
        // given
        int negativeAge = -5;

        // when
        ClientEntity foundClient = clientService.findByAge(negativeAge);

        // then
        assertThat(foundClient).isNull();
    }

    // 4. Prueba cuando la edad es cero
    @Test
    public void whenFindByAgeZero_thenReturnNullIfNotFound() {
        // given
        int age = 0;
        given(clientRepository.findByAge(age)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByAge(age);

        // then
        assertThat(foundClient).isNull();
    }

    // 5. Verificar que el repositorio es llamado una vez
    @Test
    public void whenFindByAge_thenRepositoryMethodCalledOnce() {
        // given
        int age = 30;
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", age, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByAge(age)).willReturn(client);

        // when
        clientService.findByAge(age);

        // then
        verify(clientRepository, times(1)).findByAge(age);
    }

    // 6. Prueba que el método funcione con diferentes edades
    @Test
    public void whenFindByAge_thenReturnCorrectClientBasedOnAge() {
        // given
        int age = 25;
        ClientEntity client = new ClientEntity(null, "Alice", "12345678-0", "alice@example.com", "123456780", age, 2500.0, 12000.0, "Consultant", 55000.0, 10, 3.5, "Business Loan", false, 3, "Manager", 3, 0);
        given(clientRepository.findByAge(age)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByAge(age);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getAge()).isEqualTo(age);
    }

    // 7. Prueba cuando se busca con edad no válida
    @Test
    public void whenFindByInvalidAge_thenReturnNull() {
        // given
        int age = 150; // Supongamos que 150 es un valor poco probable
        given(clientRepository.findByAge(age)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByAge(age);

        // then
        assertThat(foundClient).isNull();
    }




}









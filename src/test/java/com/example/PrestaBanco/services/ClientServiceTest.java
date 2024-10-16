package com.example.PrestaBanco.services;

import com.example.PrestaBanco.entities.ClientEntity;
import com.example.PrestaBanco.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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

    @Test
    public void whenFindByRut_thenReturnClient() {
        // given
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByRut("12345678-9")).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByRut("12345678-9");

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getRut()).isEqualTo("12345678-9");
    }

    @Test
    public void whenFindByRutNotExists_thenReturnNull() {
        // given
        given(clientRepository.findByRut("nonexistent-rut")).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByRut("nonexistent-rut");

        // then
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByEmail_thenReturnClient() {
        // given
        ClientEntity client = new ClientEntity(null, "Jane Doe", "98765432-1", "jane@example.com", "987654321", 28, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1);
        given(clientRepository.findByEmail("jane@example.com")).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByEmail("jane@example.com");

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getEmail()).isEqualTo("jane@example.com");
    }

    @Test
    public void whenFindByEmailNotExists_thenReturnNull() {
        // given
        given(clientRepository.findByEmail("nonexistent@example.com")).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByEmail("nonexistent@example.com");

        // then
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByClientId_thenReturnClient() {
        // given
        ClientEntity client = new ClientEntity(1L, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByClientId(1L)).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByClientId(1L);

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getClient_id()).isEqualTo(1L);
    }

    @Test
    public void whenFindByClientIdNotExists_thenReturnNull() {
        // given
        given(clientRepository.findByClientId(999L)).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByClientId(999L);

        // then
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByName_thenReturnClient() {
        // given
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByName("John Doe")).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByName("John Doe");

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getName()).isEqualTo("John Doe");
    }

    @Test
    public void whenFindByNameNotExists_thenReturnNull() {
        // given
        given(clientRepository.findByName("Nonexistent Name")).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByName("Nonexistent Name");

        // then
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByPhone_thenReturnClient() {
        // given
        ClientEntity client = new ClientEntity(null, "Jane Doe", "98765432-1", "jane@example.com", "987654321", 28, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1);
        given(clientRepository.findByPhone("987654321")).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByPhone("987654321");

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getPhone()).isEqualTo("987654321");
    }

    @Test
    public void whenFindByPhoneNotExists_thenReturnNull() {
        // given
        given(clientRepository.findByPhone("nonexistent-phone")).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByPhone("nonexistent-phone");

        // then
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenFindByJobType_thenReturnClient() {
        // given
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.findByJobType("Software Engineer")).willReturn(client);

        // when
        ClientEntity foundClient = clientService.findByJobType("Software Engineer");

        // then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getJob_type()).isEqualTo("Software Engineer");
    }

    @Test
    public void whenFindByJobTypeNotExists_thenReturnNull() {
        // given
        given(clientRepository.findByJobType("nonexistent-job")).willReturn(null);

        // when
        ClientEntity foundClient = clientService.findByJobType("nonexistent-job");

        // then
        assertThat(foundClient).isNull();
    }

    @Test
    public void whenSaveClient_thenReturnSavedClient() {
        // given
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientRepository.save(client)).willReturn(client);

        // when
        ClientEntity savedClient = clientService.save(client);

        // then
        assertThat(savedClient).isNotNull();
        assertThat(savedClient.getName()).isEqualTo("John Doe");
    }

    @Test
    public void whenDeleteById_thenClientDeleted() {
        // given
        Long clientId = 1L;
        doNothing().when(clientRepository).deleteById(clientId);

        // when
        clientService.deleteById(clientId);

        // then
        verify(clientRepository, times(1)).deleteById(clientId);
    }

    @Test
    public void whenDeleteClient_thenClientDeleted() {
        // given
        ClientEntity client = new ClientEntity(1L, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        doNothing().when(clientRepository).delete(client);

        // when
        clientService.delete(client);

        // then
        verify(clientRepository, times(1)).delete(client);
    }

    @Test
    public void whenDeleteAll_thenAllClientsDeleted() {
        // given
        doNothing().when(clientRepository).deleteAll();

        // when
        clientService.deleteAll();

        // then
        verify(clientRepository, times(1)).deleteAll();
    }

    @Test
    public void whenExistsById_thenReturnTrue() {
        // given
        Long clientId = 1L;
        given(clientRepository.existsById(clientId)).willReturn(true);

        // when
        boolean exists = clientService.existsById(clientId);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    public void whenExistsByIdNotExists_thenReturnFalse() {
        // given
        Long clientId = 999L;
        given(clientRepository.existsById(clientId)).willReturn(false);

        // when
        boolean exists = clientService.existsById(clientId);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    public void whenExistsByRut_thenReturnTrue() {
        // given
        String rut = "12345678-9";
        given(clientRepository.existsByRut(rut)).willReturn(true);

        // when
        boolean exists = clientService.existsByRut(rut);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    public void whenExistsByRutNotExists_thenReturnFalse() {
        // given
        String rut = "nonexistent-rut";
        given(clientRepository.existsByRut(rut)).willReturn(false);

        // when
        boolean exists = clientService.existsByRut(rut);

        // then
        assertThat(exists).isFalse();
    }
}

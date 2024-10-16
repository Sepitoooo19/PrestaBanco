package com.example.PrestaBanco.controllers;

import com.example.PrestaBanco.entities.ClientEntity;
import com.example.PrestaBanco.services.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void whenGetAllClients_thenReturnJsonArray() throws Exception {
        // given
        ClientEntity client1 = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        ClientEntity client2 = new ClientEntity(null, "Jane Doe", "98765432-1", "jane@example.com", "987654321", 28, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1);
        List<ClientEntity> clients = Arrays.asList(client1, client2);

        given(clientService.findAll()).willReturn(clients);

        // when & then
        mockMvc.perform(get("/api/v1/client"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[1].name", is("Jane Doe")));
    }

    @Test
    public void whenGetClientByRut_thenReturnJson() throws Exception {
        // given
        ClientEntity client = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientService.findByRut("12345678-9")).willReturn(client);

        // when & then
        mockMvc.perform(get("/api/v1/client/{rut}", "12345678-9"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("John Doe")));
    }

    @Test
    public void whenGetClientByRutNotFound_thenReturn404() throws Exception {
        // given
        given(clientService.findByRut("nonexistent-rut")).willReturn(null);

        // when & then
        mockMvc.perform(get("/api/v1/client/{rut}", "nonexistent-rut"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenGetClientByEmail_thenReturnJson() throws Exception {
        // given
        ClientEntity client = new ClientEntity(null, "Jane Doe", "98765432-1", "jane@example.com", "987654321", 28, 3000.0, 15000.0, "Designer", 60000.0, 24, 3.0, "Mortgage", true, 8, "Senior Designer", 2, 1);
        given(clientService.findByEmail("jane@example.com")).willReturn(client);

        // when & then
        mockMvc.perform(get("/api/v1/client/email/{email}", "jane@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Jane Doe")));
    }

    @Test
    public void whenGetClientByEmailNotFound_thenReturn404() throws Exception {
        // given
        given(clientService.findByEmail("nonexistent@example.com")).willReturn(null);

        // when & then
        mockMvc.perform(get("/api/v1/client/email/{email}", "nonexistent@example.com"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenGetClientByClientId_thenReturnJson() throws Exception {
        // given
        ClientEntity client = new ClientEntity(1L, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientService.findByClientId(1L)).willReturn(client);

        // when & then
        mockMvc.perform(get("/api/v1/client/client_id/{client_id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("John Doe")));
    }

    @Test
    public void whenGetClientByClientIdNotFound_thenReturn404() throws Exception {
        // given
        given(clientService.findByClientId(999L)).willReturn(null);

        // when & then
        mockMvc.perform(get("/api/v1/client/client_id/{client_id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenPostNewClient_thenReturnJson() throws Exception {
        // given
        ClientEntity newClient = new ClientEntity(null, "John Doe", "12345678-9", "john@example.com", "123456789", 30, 2000.0, 10000.0, "Software Engineer", 50000.0, 12, 2.5, "Personal Loan", false, 5, "Developer", 1, 0);
        given(clientService.save(Mockito.any(ClientEntity.class))).willReturn(newClient);

        // when & then
        mockMvc.perform(post("/api/v1/client/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"rut\":\"12345678-9\", \"email\":\"john@example.com\", \"phone\":\"123456789\", \"age\":30, \"monthlySalary\":2000.0, \"personalSavings\":10000.0, \"jobType\":\"Software Engineer\", \"expectedAmount\":50000.0, \"timeLimit\":12, \"typeLoan\":\"Personal Loan\", \"independentActivity\":false, \"clientId\":1, \"rating\":0}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("John Doe")));
    }
}

package com.example.PrestaBanco.controllers;

import com.example.PrestaBanco.entities.ClientEntity;
import com.example.PrestaBanco.services.BankExecutiveService;
import com.example.PrestaBanco.services.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/executives")
public class BankExecutiveController {

    @Autowired
    private BankExecutiveService bankExecutiveService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientEntity> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{rut}")
    public ResponseEntity<ClientEntity> getClientByRut(@PathVariable String rut) {
        ClientEntity client = clientService.findByRut(rut);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/{rut}/amount")
    public ResponseEntity<Double> getExpectedAmountOfClientByRut(@PathVariable String rut) {
        double expectedAmount = bankExecutiveService.getExpectedAmountOfClientByRut(rut);
        return new ResponseEntity<>(expectedAmount, HttpStatus.OK);
    }

    @GetMapping("/{rut}/interest")
    public ResponseEntity<Double> getInteresRateOfClientByRut(@PathVariable String rut) {
        double interestRate = bankExecutiveService.getInteresRateOfClientByRut(rut);
        return new ResponseEntity<>(interestRate, HttpStatus.OK);
    }

    @GetMapping("/{rut}/time")
    public ResponseEntity<Integer> getTimeLimitOfClientByRut(@PathVariable String rut) {
        int timeLimit = bankExecutiveService.getTimeLimitOfClientByRut(rut);
        return new ResponseEntity<>(timeLimit, HttpStatus.OK);
    }

    @GetMapping("/{rut}/monthly-loan")
    public ResponseEntity<?> getMonthlyLoanOfClientByRut(@PathVariable String rut) {
        try {
            double monthlyLoan = bankExecutiveService.getMonthlyLoanOfClientByRut(rut);
            return ResponseEntity.ok(monthlyLoan);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Client with RUT " + rut + " not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while calculating the monthly loan.");
        }
    }

    @GetMapping("/{rut}/monthly-salary")
    public ResponseEntity<Double> getMonthlySalaryOfClientByRut(@PathVariable String rut) {
        double monthlySalary = bankExecutiveService.getMonthlySalaryOfClientByRut(rut);
        return new ResponseEntity<>(monthlySalary, HttpStatus.OK);
    }

    @GetMapping("/{rut}/fee-income")
    public ResponseEntity<Double> getFeeIncomeOfClientByRut(@PathVariable String rut) {
        double feeIncome = bankExecutiveService.getFeeIncomeRatioByRut(rut);
        return new ResponseEntity<>(feeIncome, HttpStatus.OK);
    }

    @GetMapping("/{rut}/debt-amount")
    public ResponseEntity<Double> getDebtAmountByRut(@PathVariable String rut) {
        double debtAmount = bankExecutiveService.getDebtAmountByRut(rut);
        return new ResponseEntity<>(debtAmount, HttpStatus.OK);
    }






}


package com.example.PrestaBanco.controllers;

import com.example.PrestaBanco.entities.*;
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

    @GetMapping("/{rut}/debts")
    public ResponseEntity<List<DebtEntity>> getDebtsByRut(@PathVariable String rut) {
        List<DebtEntity> debts = bankExecutiveService.getDebtsByRut(rut);
        return new ResponseEntity<>(debts, HttpStatus.OK);
    }

    @GetMapping("/{rut}/employment-history")
    public ResponseEntity<List<EmploymentHistoryEntity>> getEmploymentHistoryByRut(@PathVariable String rut) {
        List<EmploymentHistoryEntity> employmentHistory = bankExecutiveService.getEmploymentHistoryByRut(rut);
        return new ResponseEntity<>(employmentHistory, HttpStatus.OK);

    }

    @GetMapping("/{rut}/credit-application")
    public ResponseEntity<List<CreditApplicationEntity>> getCreditApplicationByRut(@PathVariable String rut) {

        List<CreditApplicationEntity> creditApplication = bankExecutiveService.getCreditApplicationsByRut(rut);
        return new ResponseEntity<>(creditApplication, HttpStatus.OK);

    }

    @GetMapping("/{rut}/loan")
    public ResponseEntity<List<LoanEntity>> getLoanByRut(@PathVariable String rut) {

        List<LoanEntity> loan = bankExecutiveService.getLoansByRut(rut);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @GetMapping("/{rut}/client-bank-account")
    public ResponseEntity<List<ClientBankAccountEntity>> getClientBankAccountByRut(@PathVariable String rut) {
        List<ClientBankAccountEntity> clientBankAccount = bankExecutiveService.getClientBankAccountsByRut(rut);
        return new ResponseEntity<>(clientBankAccount, HttpStatus.OK);
    }

    @GetMapping("/{rut}/deposit")
    public ResponseEntity<Integer> getDepositInBankAccountByRut(@PathVariable String rut) {
        int deposit = bankExecutiveService.getDepositInBankAccountByRut(rut);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }

    @GetMapping("/{rut}/withdrawal")
    public ResponseEntity<Integer> getWithdrawalInBankAccountByRut(@PathVariable String rut) {
        int withdrawal = bankExecutiveService.getWithdrawalInBankAccountByRut(rut);
        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("/validate-age/{rut}")
    public ResponseEntity<String> validateLoanAge(@PathVariable String rut) {

        boolean isValid = bankExecutiveService.getAgeAndVerifyMaximumAgeByRut(rut);

        if (isValid) {
            return ResponseEntity.ok("El préstamo es válido según la edad del cliente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El préstamo es rechazado debido a la edad del cliente.");
        }
    }

    @GetMapping("/validate-balance/{rut}")
    public ResponseEntity<String> validateBankAccountBalance(@PathVariable String rut) {
        boolean isValid = bankExecutiveService.isBankAccountBalanceTenPercentageOfMonthlyFeeByRut(rut);

        if (isValid) {
            return ResponseEntity.ok("El saldo de la cuenta bancaria del cliente es suficiente para cubrir al menos el 10% de la cuota mensual.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El saldo de la cuenta bancaria del cliente no es suficiente para cubrir el 10% de la cuota mensual.");
        }
    }

    @GetMapping("/validate-bank-consistency/{rut}")
    public ResponseEntity<String> validateBankAccountConsistency(@PathVariable String rut) {
        boolean isConsistent = bankExecutiveService.isBankAccountConsistentByRut(rut);

        if (isConsistent) {
            return ResponseEntity.ok("La cuenta bancaria del cliente es consistente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Las cuentas bancarias del cliente no son consistentes.");
        }
    }

    @GetMapping("/{rut}/periodic-deposits")
    public ResponseEntity<String> checkPeriodicDeposits(@PathVariable String rut) {
        // Verificar si la cuenta bancaria es consistente según el RUT proporcionado
        boolean isConsistent = bankExecutiveService.isBankAccountConsistentByRut(rut);

        // Retornar respuesta según la consistencia de la cuenta bancaria
        if (isConsistent) {
            return ResponseEntity.ok("El cliente cumple con los depositos periódicos."); // Respuesta positiva
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El cliente no cumple con los depositos periódicos."); // Respuesta negativa
        }
    }










}


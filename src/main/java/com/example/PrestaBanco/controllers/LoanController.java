package com.example.PrestaBanco.controllers;
import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.PrestaBanco.entities.LoanEntity;
import com.example.PrestaBanco.services.LoanService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/loan")
@CrossOrigin("*")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanEntity>> findAll() {
        return ResponseEntity.ok(loanService.findAll());
    }

    @GetMapping("/{loan_id}")
    public ResponseEntity<LoanEntity> findByLoanId(@PathVariable Long loan_id) {
        return ResponseEntity.ok(loanService.findByLoanId(loan_id));
    }

    @GetMapping("/amount/{loan_amount}")
    public ResponseEntity<LoanEntity> findByAmount(@PathVariable double loan_amount) {
        return ResponseEntity.ok(loanService.findByAmount(loan_amount));
    }

    @GetMapping("/time_limit/{max_time_limit}")
    public ResponseEntity<LoanEntity> findByTimeLimit(@PathVariable int max_time_limit) {
        return ResponseEntity.ok(loanService.findByTimeLimit(max_time_limit));
    }

    @GetMapping("/interest_rate/{min_interest_rate}")
    public ResponseEntity<LoanEntity> findByInterestRate(@PathVariable double min_interest_rate) {
        return ResponseEntity.ok(loanService.findByInterestRate(min_interest_rate));
    }

    @GetMapping("/client_id/{client_id}")
    public ResponseEntity<List<LoanEntity>> findByClientId(@PathVariable Long client_id) {
        return ResponseEntity.ok(loanService.findByClientId(client_id));
    }

    @GetMapping("/loan_type/{loan_type}")
    public ResponseEntity<LoanEntity> findByLoanType(@PathVariable String loan_type) {
        return ResponseEntity.ok(loanService.findByLoanType(loan_type));
    }

    @GetMapping("/loan_date/{loan_date}")
    public ResponseEntity<LoanEntity> findByLoanDate(@PathVariable String loan_date) {
        return ResponseEntity.ok(loanService.findByLoanDate(loan_date));
    }

    @GetMapping("/loan_status/{loan_status}")
    public ResponseEntity<LoanEntity> findByLoanStatus(@PathVariable String loan_status) {
        return ResponseEntity.ok(loanService.findByLoanStatus(loan_status));
    }

    @GetMapping("/max_interest_rate/{max_interest_rate}")
    public ResponseEntity<LoanEntity> findByMaxInterestRate(@PathVariable double max_interest_rate) {
        return ResponseEntity.ok(loanService.findByMaxInterestRate(max_interest_rate));
    }

    @GetMapping("/max_financing_amount/{max_financing_amount}")
    public ResponseEntity<LoanEntity> findByMaxFinancingAmount(@PathVariable double max_financing_amount) {
        return ResponseEntity.ok(loanService.findByMaxFinancingAmount(max_financing_amount));
    }

    @GetMapping("/credit_application_id/{credit_application_id}")
    public ResponseEntity<LoanEntity> findByCreditApplicationId(@PathVariable Long credit_application_id) {
        return ResponseEntity.ok(loanService.findByCreditApplicationId(credit_application_id));
    }

    @PostMapping("/create")
    public ResponseEntity<LoanEntity> createLoan(@RequestBody LoanEntity loan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.save(loan));
    }

    @PostMapping("/create_loans_by_rut")
    public ResponseEntity<List<LoanEntity>> createLoansByRut(@RequestBody Map<String, String> requestBody) {
        String rut = requestBody.get("rut");

        try {
            List<LoanEntity> createdLoans = loanService.setDetailsOfCreditApplicationByRut(rut);
            if (createdLoans == null || createdLoans.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // No se encontraron solicitudes de crédito
            }
            return new ResponseEntity<>(createdLoans, HttpStatus.CREATED);  // Retorna todos los préstamos creados
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // No se encontró el cliente
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Maneja otros errores
        }
    }
}

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
@CrossOrigin("*")
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
    public ResponseEntity<String> getFeeIncomeOfClientByRut(@PathVariable String rut) {
        double feeIncomeRatio = bankExecutiveService.getFeeIncomeRatioByRut(rut);

        String message;
        if (feeIncomeRatio > 35) {
            message = "La solicitud ha sido rechazada: la relación cuota/ingreso es " + feeIncomeRatio + "%, que supera el límite permitido del 35%.";
        } else {
            message = "La solicitud está dentro del límite y será considerada para aprobación. Relación cuota/ingreso: " + feeIncomeRatio + "%.";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
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

    @GetMapping("/{rut}/jobSeniority-amountRatio")
    public ResponseEntity<String> checkJobSeniorityAmountRatio(@PathVariable String rut) {
        // Verificar si la cuenta bancaria es consistente según el RUT proporcionado
        boolean isConsistent = bankExecutiveService.isBankAccountConsistentByRut(rut);

        // Retornar respuesta según la consistencia de la cuenta bancaria
        if (isConsistent) {
            return ResponseEntity.ok("El cliente cumple con la relación de antigüedad laboral y monto de ingreso."); // Respuesta positiva
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El cliente no cumple con la relación de antigüedad laboral y monto de ingreso."); // Respuesta negativa
        }
    }

    @GetMapping("/check-large-withdrawals/{rut}")
    public ResponseEntity<String> checkLargeWithdrawals(@PathVariable String rut) {
        boolean hasLargeWithdrawals = bankExecutiveService.checkRecentWithdrawalsByRut(rut);

        if (hasLargeWithdrawals) {
            return ResponseEntity.ok("El cliente ha realizado un retiro superior al 30% del saldo en los últimos 6 meses.");
        } else {
            return ResponseEntity.ok("El cliente no ha realizado retiros significativos en los últimos 6 meses.");
        }
    }

    @GetMapping("/evaluate/{rut}")
    public ResponseEntity<String> evaluateClient(@PathVariable String rut) {
        int result = bankExecutiveService.checkResultsEvaluationByRut(rut);

        String evaluationStatus;

        // Aprobación: Cumple con las 5 reglas
        if (result == 5) {
            evaluationStatus = "La capacidad de ahorro es sólida. Se puede continuar con la evaluación del crédito.";
        }
        // Revisión Adicional: Cumple con 3 o 4 reglas
        else if (result >= 3 && result < 5) {
            evaluationStatus = "La capacidad de ahorro es moderada. Se requiere una revisión adicional.";
        }
        // Rechazo: Cumple con menos de 2 reglas
        else {
            evaluationStatus = "La capacidad de ahorro es insuficiente. Se procede a rechazar la solicitud.";
        }

        return ResponseEntity.ok(evaluationStatus);
    }

    @GetMapping("/insurance/{rut}")
    public ResponseEntity<Integer> calculateInsurance(@PathVariable String rut) {
        int insurance = bankExecutiveService.insuranceCalculationByRut(rut);
        return ResponseEntity.ok(insurance);
    }

    @GetMapping("/total-cost/{rut}")
    public ResponseEntity<String> calculateTotalCost(@PathVariable String rut) {

        // Obtener el costo total del préstamo
        int totalCost = bankExecutiveService.totalCostOfLoanByRut(rut);

        // Crear el mensaje que incluye el total y una explicación
        String message = String.format("El costo total del préstamo, considerando todas las cuotas, seguros y la comisión inicial, es de $%,d.", totalCost);

        // Devolver el mensaje como respuesta
        return ResponseEntity.ok(message);
    }

    @GetMapping("/administration-commission/{rut}")
    public ResponseEntity<Integer> calculateAdministrationCommission(@PathVariable String rut) {
        int commission = bankExecutiveService.administrationCommissionByRut(rut);
        return ResponseEntity.ok(commission);
    }

    @GetMapping("/monthly-cost/{rut}")
    public ResponseEntity<Integer> calculateMonthlyCost(@PathVariable String rut) {
        int monthlyCost = bankExecutiveService.monthlyCostByRut(rut);
        return ResponseEntity.ok(monthlyCost);
    }



}


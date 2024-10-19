package com.example.PrestaBanco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PrestaBanco.repositories.ClientRepository;
import com.example.PrestaBanco.entities.ClientEntity;
import java.util.List;
import com.example.PrestaBanco.repositories.BanExecutiveRepository;
import com.example.PrestaBanco.entities.BankExecutiveEntity;
import com.example.PrestaBanco.repositories.DebtRepository;
import com.example.PrestaBanco.entities.DebtEntity;
import com.example.PrestaBanco.entities.EmploymentHistoryEntity;
import com.example.PrestaBanco.repositories.EmploymentHistoryRepository;
import com.example.PrestaBanco.entities.CreditApplicationEntity;
import com.example.PrestaBanco.repositories.CreditApplicationRepository;
import com.example.PrestaBanco.entities.LoanEntity;
import com.example.PrestaBanco.repositories.LoanRepository;
import com.example.PrestaBanco.entities.ClientBankAccountEntity;
import com.example.PrestaBanco.repositories.ClientBankAccountRepository;
import java.util.Arrays;
import java.time.LocalDate;



@Service
public class BankExecutiveService {

    @Autowired
    private BanExecutiveRepository bankExecutiveRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DebtRepository debtRepository;

    @Autowired
    private EmploymentHistoryRepository employmentHistoryRepository;

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientBankAccountRepository clientBankAccountRepository;

    public List<BankExecutiveEntity> findAll() {
        return bankExecutiveRepository.findAll();
    }

    public BankExecutiveEntity findByRut(String rut) {
        return bankExecutiveRepository.findByRut(rut);
    }

    public BankExecutiveEntity findByEmail(String email) {
        return bankExecutiveRepository.findByEmail(email);
    }

    public BankExecutiveEntity findByExecutiveId(Long executive_id) {
        return bankExecutiveRepository.findByExecutiveId(executive_id);
    }

    public BankExecutiveEntity findByName(String name) {
        return bankExecutiveRepository.findByName(name);
    }

    public BankExecutiveEntity findByPhone(String phone) {
        return bankExecutiveRepository.findByPhone(phone);
    }

    public boolean existsByRut(String rut) {
        return bankExecutiveRepository.existsByRut(rut);
    }

    public double getExpectedAmountOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getExpected_amount();

    }

    public double getInteresRateOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getInterest_rate();
    }

    public int getTimeLimitOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getTime_limit();
    }

    public double getMonthlySalaryOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getMonthly_salary();
    }

    public int getMonthlyLoanOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        double interest_rate = client.getInterest_rate() / 12 / 100;
        double expected_amount = client.getExpected_amount();
        int time_limit_in_months = client.getTime_limit() * 12;
        double monthly_fee = expected_amount * ((interest_rate*(Math.pow(1+interest_rate, time_limit_in_months)))/(Math.pow(1+interest_rate, time_limit_in_months)-1));
        return (int) monthly_fee;
        
    }

    public double getFeeIncomeRatioByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        double monthly_salary = client.getMonthly_salary();
        double monthly_fee = getMonthlyLoanOfClientByRut(rut);
        return (monthly_salary/ monthly_fee)* 100;
    }



    public double getDebtAmountByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        List<DebtEntity> debts = debtRepository.findByClientId(client.getClient_id());

        if(client.getClient_id() == null) {
            return 0;
        }

        double debt_amount = 0;
        for (DebtEntity debt : debts) {
            debt_amount += debt.getDebt_amount();
        }
        return debt_amount;

    }

    public List<DebtEntity> getDebtsByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return debtRepository.findByClientId(client.getClient_id());
    }

    public List<EmploymentHistoryEntity> getEmploymentHistoryByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return (List<EmploymentHistoryEntity>) employmentHistoryRepository.findByClientId(client.getClient_id());
    }

    public List<CreditApplicationEntity> getCreditApplicationsByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return creditApplicationRepository.findByClientId(client.getClient_id());
    }

    public List<LoanEntity> getLoansByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return loanRepository.findByClientId(client.getClient_id());
    }

    public List<ClientBankAccountEntity> getClientBankAccountsByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return clientBankAccountRepository.findByClientId(client.getClient_id());
    }



    //obtener deposito total en el banco si transaction es deposit
    public int getDepositInBankAccountByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        List<ClientBankAccountEntity> clientBankAccounts = clientBankAccountRepository.findByClientId(client.getClient_id());
        int deposit = 0;
        for (ClientBankAccountEntity clientBankAccount : clientBankAccounts) {
            if (clientBankAccount.getTransaction().equals("deposit")) {
                deposit += clientBankAccount.getAmount();
            }
        }
        return deposit;
    }

    //obtener retiro total en el banco si transaction es withdrawal
    public int getWithdrawalInBankAccountByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        List<ClientBankAccountEntity> clientBankAccounts = clientBankAccountRepository.findByClientId(client.getClient_id());
        int withdrawal = 0;
        for (ClientBankAccountEntity clientBankAccount : clientBankAccounts) {
            if (clientBankAccount.getTransaction().equals("withdrawal")) {
                withdrawal += clientBankAccount.getAmount();
            }
        }
        return withdrawal;
    }

    public boolean getAgeAndVerifyMaximumAgeByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        int age = client.getAge();


        // Si la edad actual es menor de 18, no se puede otorgar el préstamo
        if (age < 18) {
            return false;
        }

        // Si el cliente tiene 5 años o menos antes de alcanzar los 75 años al final del préstamo, se rechaza
        if (75 - age < 5) {
            return false;
        }

        // Caso contrario, el préstamo es aceptado
        return true;
    }



    public boolean isBankAccountBalanceTenPercentageOfMonthlyFeeByRut(String rut) {

        ClientEntity client = clientRepository.findByRut(rut);
        List<ClientBankAccountEntity> clientBankAccounts = clientBankAccountRepository.findByClientId(client.getClient_id());
        double monthly_fee = getMonthlyLoanOfClientByRut(rut);
        int deposit = 0;
        for (ClientBankAccountEntity clientBankAccount : clientBankAccounts) {
            if (clientBankAccount.getTransaction().equals("deposit")) {
                deposit += clientBankAccount.getAmount();
            }
        }
        if (deposit >= monthly_fee * 0.1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isBankAccountConsistentByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        List<ClientBankAccountEntity> clientBankAccounts = clientBankAccountRepository.findByClientId(client.getClient_id());
        int deposit = 0;
        int withdrawal = 0;
        for (ClientBankAccountEntity clientBankAccount : clientBankAccounts) {
            if(clientBankAccount.getAmount() == 0){
                return false;
            }
            else{
                if (clientBankAccount.getTransaction().equals("deposit")) {
                    deposit += clientBankAccount.getAmount();
                } else {
                    withdrawal += clientBankAccount.getAmount();
                }
            }
        }
        int total = deposit + withdrawal;

        for (ClientBankAccountEntity clientBankAccount : clientBankAccounts) {
            if (clientBankAccount.getTransaction().equals("withdrawal")) {
                if (clientBankAccount.getAmount() > total * 0.5) {
                    return false;
                }
            }
        }

        if ( withdrawal > total * 0.5){
            return false;
        } else {
            return true;
        }
    }

    public boolean containsBankAccountPeriodicDepositsByRut(String rut) {
        // Obtener el cliente por su rut
        ClientEntity client = clientRepository.findByRut(rut);
        List<ClientBankAccountEntity> clientBankAccounts = clientBankAccountRepository.findByClientId(client.getClient_id());

        // Fecha actual y hace 12 meses
        LocalDate now = LocalDate.now();
        LocalDate twelveMonthsAgo = now.minusMonths(12);

        // Ingresos mensuales del cliente
        double monthlyIncome = client.getMonthly_salary();
        double minimumRequiredDeposit = monthlyIncome * 0.05;

        // Variables para verificar depósitos regulares
        boolean hasRegularDeposits = false;
        double totalDeposits = 0;
        // Arreglo para marcar si se realizó un depósito en cada mes
        boolean[] monthsWithDeposits = new boolean[12]; // 12 meses
        Arrays.fill(monthsWithDeposits, false);
        // Recorremos todas las transacciones del cliente
        for (ClientBankAccountEntity account : clientBankAccounts) {
            // Verificamos si es un depósito
            if (account.getTransaction().equalsIgnoreCase("deposit")) {
                // Verificamos si la fecha está dentro de los últimos 12 meses
                LocalDate transactionDate = account.getTransaction_date();
                if ((transactionDate.isAfter(twelveMonthsAgo) || transactionDate.isEqual(twelveMonthsAgo))
                        && transactionDate.isBefore(now.plusDays(1))) {
                    // Calcular la diferencia de meses entre la fecha actual y la transacción
                    int monthsDifference = now.getMonthValue() - transactionDate.getMonthValue() +
                            (now.getYear() - transactionDate.getYear()) * 12;
                    // Si está dentro de los 12 meses, marcamos el depósito para ese mes
                    if (monthsDifference >= 0 && monthsDifference < 12) {
                        monthsWithDeposits[monthsDifference] = true;
                    }
                    // Sumar el monto del depósito
                    totalDeposits += account.getAmount();
                }
            }
        }
        // Contar los meses en los que hubo depósitos
        int monthsWithDepositsCount = 0;
        for (boolean hasDeposit : monthsWithDeposits) {
            if (hasDeposit) {
                monthsWithDepositsCount++;
            }
        }
        // Verificar si hubo depósitos al menos en 4 meses diferentes (frecuencia trimestral)
        if (monthsWithDepositsCount >= 4) {
            hasRegularDeposits = true;
        }
        // Verificar si cumple con el monto mínimo de depósitos
        if (hasRegularDeposits && totalDeposits >= minimumRequiredDeposit) {
            return true; // Cumple con los requisitos
        } else {
            return false; // No cumple
        }
    }

    











}

package com.example.PrestaBanco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Entity
@Table(name = "Loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_id;

    @Column(name = "client_id")
    private Long client_id;

    @Column(name = "loan_amount")
    private double loan_amount;

    @Column(name = "loan_type")
    private String loan_type;

    @Column(name = "loan_date")
    private String loan_date;

    @Column(name = "loan_status")
    private String loan_status;

    @Column(name = "max_time_limit")
    private int max_time_limit;

    @Column(name = "min_interest_rate")
    private double min_interest_rate;

    @Column(name = "max_interest_rate")
    private double max_interest_rate;

    @Column(name = "max_financing_amount")
    private double max_financing_amount;

    @Column(name = "credit_application_id")
    private Long credit_application_id;

}

package com.example.PrestaBanco.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Entity
@Table(name = "CreditApplication")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long credit_application_id;

    @Column(name = "client_id")
    private Long client_id;

    @Column(name = "name")
    private String name;

    @Column(name = "credit_date")
    private String credit_date;

    @Column(name = "document_1")
    private boolean document_1;

    @Column(name = "document_2")
    private boolean document_2;

    @Column(name = "document_3")
    private boolean document_3;

    @Column(name = "document_4")
    private boolean document_4;

    @Column(name = "status")
    private String status;




}

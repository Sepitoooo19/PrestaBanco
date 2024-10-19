package com.example.PrestaBanco.controllers;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.PrestaBanco.entities.CreditApplicationEntity;
import com.example.PrestaBanco.services.CreditApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credit_application")
@CrossOrigin("*")
public class CreditApplicationController {

    @Autowired
    private CreditApplicationService creditApplicationService;

    @GetMapping("/client_id/{client_id}")
    public ResponseEntity<List<CreditApplicationEntity>> findByClientId(@PathVariable Long client_id) {
        return ResponseEntity.ok(creditApplicationService.findByClientId(client_id));
    }

    @GetMapping("/credit_application_id/{credit_application_id}")
    public ResponseEntity<CreditApplicationEntity> findByCreditApplicationId(@PathVariable Long credit_application_id) {
        return ResponseEntity.ok(creditApplicationService.findByCreditApplicationId(credit_application_id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CreditApplicationEntity> findByName(@PathVariable String name) {
        return ResponseEntity.ok(creditApplicationService.findByName(name));
    }

    @GetMapping("/credit_date/{credit_date}")
    public ResponseEntity<CreditApplicationEntity> findByCreditDate(@PathVariable String credit_date) {
        return ResponseEntity.ok(creditApplicationService.findByCreditDate(credit_date));
    }

    @GetMapping("/document_1/{document_1}")
    public ResponseEntity<CreditApplicationEntity> findByDocument1(@PathVariable boolean document_1) {
        return ResponseEntity.ok(creditApplicationService.findByDocument1(document_1));
    }

    @GetMapping("/document_2/{document_2}")
    public ResponseEntity<CreditApplicationEntity> findByDocument2(@PathVariable boolean document_2) {
        return ResponseEntity.ok(creditApplicationService.findByDocument2(document_2));
    }

    @GetMapping("/document_3/{document_3}")
    public ResponseEntity<CreditApplicationEntity> findByDocument3(@PathVariable boolean document_3) {
        return ResponseEntity.ok(creditApplicationService.findByDocument3(document_3));
    }

    @GetMapping("/document_4/{document_4}")
    public ResponseEntity<CreditApplicationEntity> findByDocument4(@PathVariable boolean document_4) {
        return ResponseEntity.ok(creditApplicationService.findByDocument4(document_4));
    }

    @GetMapping("/exists/client_id/{client_id}")
    public ResponseEntity<Boolean> existsByClientId(@PathVariable Long client_id) {
        return ResponseEntity.ok(creditApplicationService.existsByClientId(client_id));
    }


}

package com.example.PrestaBanco.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PrestaBanco.repositories.DocumentRepository;
import com.example.PrestaBanco.entities.DocumentEntity;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;


    public List<DocumentEntity> findAll() {
        return documentRepository.findAll();
    }

    public DocumentEntity findByDocumentId(Long document_id) {
        return documentRepository.findByDocumentId(document_id);
    }

    public DocumentEntity findByType(String document_type) {
        return documentRepository.findByType(document_type);
    }


    public DocumentEntity findBySubmitDate(LocalDateTime submitDate) {
        return documentRepository.findBySubmitDate(submitDate);
    }

    @Transactional
    public List<DocumentEntity> findByClientId(Long clientId) {
        return documentRepository.findByClientId(clientId);
    }

    public boolean existsByDocumentId(Long document_id) {
        return documentRepository.existsByDocumentId(document_id);
    }

    public boolean existsByClientId(Long client_id) {
        return documentRepository.existsByClientId(client_id);
    }

    @Transactional
    public DocumentEntity save(DocumentEntity document) {
        return documentRepository.save(document);
    }




}

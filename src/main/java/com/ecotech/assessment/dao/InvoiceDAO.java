package com.ecotech.assessment.dao;

import com.ecotech.assessment.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

public interface InvoiceDAO extends JpaRepository<Invoice, String>, JpaSpecificationExecutor {
    Optional<Invoice> findByLinkId(String agentId);
}

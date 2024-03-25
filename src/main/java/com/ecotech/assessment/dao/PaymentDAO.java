package com.ecotech.assessment.dao;

import com.ecotech.assessment.models.Invoice;
import com.ecotech.assessment.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentDAO extends JpaRepository<Payment, String>, JpaSpecificationExecutor {
}

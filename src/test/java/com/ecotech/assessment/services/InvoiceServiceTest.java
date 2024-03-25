package com.ecotech.assessment.services;


import com.ecotech.assessment.dao.InvoiceDAO;
import com.ecotech.assessment.dao.PaymentDAO;
import com.ecotech.assessment.exceptions.InvoiceNotFoundException;
import com.ecotech.assessment.models.Invoice;
import com.ecotech.assessment.models.ResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InvoiceServiceTest {

    @InjectMocks
    InvoiceService invoiceService;

    @Mock
    InvoiceDAO invoiceDAO;

    @Mock
    PaymentService paymentService;

    @Mock
    PaymentDAO paymentDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByLinkId_Success() {
        // Given
        String linkId = "someLinkId";
        Invoice mockInvoice = new Invoice(); // Set up your invoice
        when(invoiceDAO.findByLinkId(linkId)).thenReturn(Optional.of(mockInvoice));

        // When
        ResponseEntity<ResponseModel<Invoice>> response = invoiceService.findByLinkId(linkId);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("00", response.getBody().getStatus());
        assertEquals(mockInvoice, response.getBody().getData());
    }

    @Test
    void findByLinkId_NotFound() {
        // Given
        String linkId = "nonExistingLinkId";
        when(invoiceDAO.findByLinkId(linkId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(InvoiceNotFoundException.class, () -> invoiceService.findByLinkId(linkId));
    }
}

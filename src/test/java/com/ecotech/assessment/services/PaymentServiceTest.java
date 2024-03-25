package com.ecotech.assessment.services;


import com.ecotech.assessment.dao.InvoiceDAO;
import com.ecotech.assessment.dao.PaymentDAO;
import com.ecotech.assessment.dto.PayInvoiceDTO;
import com.ecotech.assessment.enums.PaymentChannel;
import com.ecotech.assessment.enums.PaymentProcessingStatus;
import com.ecotech.assessment.exceptions.InvoiceNotFoundException;
import com.ecotech.assessment.models.Invoice;
import com.ecotech.assessment.models.Payment;
import com.ecotech.assessment.models.ResponseModel;
import com.ecotech.assessment.models.payment.ProcessPaymentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PaymentServiceTest {

    @Mock
    private InvoiceDAO invoiceDAO;

    @Mock
    private PaymentDAO paymentDAO;

    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void payInvoice_SuccessfulPayment() {

        String reference = "ref101";
        PayInvoiceDTO payInvoiceDTO = PayInvoiceDTO.builder()
                .invoiceId("ref1111")
                .paymentChannel(PaymentChannel.CARD)
                .build();

        Invoice mockInvoice = Invoice.builder()
                .reference(reference)
                .amount(BigDecimal.valueOf(3000))
                .customerEmail("customer@test.com")
                .customerName("John Doe")
                .customerPhoneNumber("23490889978")
                .build();

        ProcessPaymentResponse mockPaymentResponse =  ProcessPaymentResponse.builder()
                .reference(reference)
                .status(PaymentProcessingStatus.SUCCESS)
                .build();

        when(invoiceDAO.findById(any())).thenReturn(Optional.of(mockInvoice));
        when(paymentService.paywithCard(any())).thenReturn(mockPaymentResponse);
        doNothing().when(paymentDAO).save(any(Payment.class));
        when(invoiceDAO.save(any())).thenReturn(mockInvoice);

        // Execute
        ResponseEntity<ResponseModel<ProcessPaymentResponse>> response = invoiceService.payInvoice(payInvoiceDTO);

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("00", response.getBody().getStatus());
        assertEquals(PaymentProcessingStatus.SUCCESS, response.getBody().getData().getStatus());
    }

    @Test
    void payInvoice_InvoiceNotFound() {

        PayInvoiceDTO payInvoiceDTO =  PayInvoiceDTO.builder()
                .invoiceId("ref1111")
                .paymentChannel(PaymentChannel.CARD)
                .build();
        when(invoiceDAO.findById(any())).thenReturn(Optional.empty());
        assertThrows(InvoiceNotFoundException.class, () -> invoiceService.payInvoice(payInvoiceDTO));
    }

}

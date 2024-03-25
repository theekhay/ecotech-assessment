package com.ecotech.assessment.services;

import com.ecotech.assessment.dao.InvoiceDAO;
import com.ecotech.assessment.dao.PaymentDAO;
import com.ecotech.assessment.dto.PayInvoiceDTO;
import com.ecotech.assessment.enums.InvoiceStatus;
import com.ecotech.assessment.enums.PaymentChannel;
import com.ecotech.assessment.enums.PaymentProcessingStatus;
import com.ecotech.assessment.exceptions.InvoiceNotFoundException;
import com.ecotech.assessment.models.Invoice;
import com.ecotech.assessment.models.Payment;
import com.ecotech.assessment.models.ResponseModel;
import com.ecotech.assessment.models.payment.ProcessCardPaymentRequest;
import com.ecotech.assessment.models.payment.ProcessPaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class InvoiceService {

    private final InvoiceDAO invoiceDAO;
    private final PaymentService paymentService;
    private final PaymentDAO paymentDAO;

    @Autowired
    public InvoiceService(InvoiceDAO invoiceDAO, PaymentService paymentService, PaymentDAO paymentDAO) {
        this.invoiceDAO = invoiceDAO;
        this.paymentService = paymentService;
        this.paymentDAO = paymentDAO;
    }

    public ResponseEntity<ResponseModel<Invoice>> findByLinkId(String linkId) {

        Optional<Invoice> invoice = invoiceDAO.findByLinkId(linkId);
        if(invoice.isPresent()){
            ResponseModel response = ResponseModel.builder()
                    .status("00")
                    .message("Invoice fetched successfully")
                    .data(invoice.get())
                    .build();

            return ResponseEntity.ok(response);
        }

        throw new InvoiceNotFoundException();
    }

    public Invoice findById(String invoiceId){
        return invoiceDAO.findById(invoiceId).orElseThrow();
    }

    public ResponseEntity<ResponseModel<ProcessPaymentResponse>> payInvoice(PayInvoiceDTO payInvoiceDTO){
        //mocking for card payments only.

        Optional<Invoice> optInvoice = invoiceDAO.findById(payInvoiceDTO.getInvoiceId());

        if(optInvoice.isPresent()){

            Invoice invoice = optInvoice.get();
            Payment payment = buildPaymentData(invoice);
            ProcessCardPaymentRequest request = ProcessCardPaymentRequest.builder()
                    .amount(invoice.getAmount())
                    .traceId(invoice.getReference())
                    .build();

            payment.setPaymentRequest(String.valueOf(request));
            ProcessPaymentResponse paymentResponse = this.paymentService.paywithCard(request);
            payment.setPaymentResponse(String.valueOf(paymentResponse));
            paymentDAO.save(payment);

            if(paymentResponse.getStatus().equals(PaymentProcessingStatus.SUCCESS)){
                invoice.setStatus(InvoiceStatus.FULFILLED);
                invoice.setFulfilledOn(new Date());
                invoiceDAO.save(invoice);
            }

            ResponseModel response = ResponseModel.builder()
                    .status("00")
                    .message("Payment successful")
                    .data(paymentResponse)
                    .build();

            return ResponseEntity.ok(response);
        }

        throw new InvoiceNotFoundException();
    }

    private Payment buildPaymentData(Invoice invoice){

        return Payment.builder()
                .amount(invoice.getAmount())
                .channel(PaymentChannel.CARD)
                .reference(invoice.getReference())
                .build();
    }
}

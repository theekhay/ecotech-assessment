package com.ecotech.assessment.controllers;


import com.ecotech.assessment.dto.PayInvoiceDTO;
import com.ecotech.assessment.models.Invoice;
import com.ecotech.assessment.models.ResponseModel;
import com.ecotech.assessment.models.payment.ProcessPaymentResponse;
import com.ecotech.assessment.services.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{invoiceId}")
    public ResponseEntity<ResponseModel<Invoice>> fetchLoan(@PathVariable("invoiceId") String invoiceId){
        return invoiceService.findByLinkId(invoiceId);
    }

    @PostMapping("pay")
    public ResponseEntity<ResponseModel<ProcessPaymentResponse>> fulfillInvoice(@Validated @RequestBody PayInvoiceDTO payInvoiceDTO){
        return invoiceService.payInvoice(payInvoiceDTO);
    }
}

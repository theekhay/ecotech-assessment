package com.ecotech.assessment.dto;


import com.ecotech.assessment.enums.PaymentChannel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayInvoiceDTO {

    @NotNull( message = "invoice Id is required!")
    private String invoiceId;

    @NotNull( message = "paymentChannel is required!")
    private PaymentChannel paymentChannel;

}

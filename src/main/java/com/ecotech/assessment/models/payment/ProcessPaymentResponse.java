package com.ecotech.assessment.models.payment;


import com.ecotech.assessment.enums.PaymentProcessingStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessPaymentResponse {

    private PaymentProcessingStatus status;
    private String reference;

    //other info

}

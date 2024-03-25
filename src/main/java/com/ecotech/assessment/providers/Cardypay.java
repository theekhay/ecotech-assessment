package com.ecotech.assessment.providers;

import com.ecotech.assessment.enums.PaymentProcessingStatus;
import com.ecotech.assessment.interfaces.ICardPaymentProcessor;
import com.ecotech.assessment.models.payment.ProcessCardPaymentRequest;
import com.ecotech.assessment.models.payment.ProcessPaymentResponse;
import org.springframework.stereotype.Service;


@Service
public class Cardypay implements ICardPaymentProcessor {

    @Override
    public ProcessPaymentResponse processCardPayment(ProcessCardPaymentRequest processCardPaymentRequest) {
        return ProcessPaymentResponse.builder()
                .status(PaymentProcessingStatus.SUCCESS)
                .reference(processCardPaymentRequest.getTraceId())
                .build();
    }
}

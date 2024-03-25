package com.ecotech.assessment.services;


import com.ecotech.assessment.interfaces.ICardPaymentProcessor;
import com.ecotech.assessment.models.payment.ProcessCardPaymentRequest;
import com.ecotech.assessment.models.payment.ProcessPaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    private final ICardPaymentProcessor cardPaymentProcessor;

    @Autowired
    public PaymentService(ICardPaymentProcessor cardPaymentProcessor) {
        this.cardPaymentProcessor = cardPaymentProcessor;
    }

    public ProcessPaymentResponse paywithCard(ProcessCardPaymentRequest processCardPaymentRequest){
        return cardPaymentProcessor.processCardPayment(processCardPaymentRequest);
    }
}

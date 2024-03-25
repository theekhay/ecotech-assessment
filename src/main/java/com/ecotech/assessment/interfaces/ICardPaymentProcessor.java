package com.ecotech.assessment.interfaces;

import com.ecotech.assessment.models.payment.ProcessCardPaymentRequest;
import com.ecotech.assessment.models.payment.ProcessPaymentResponse;

public interface ICardPaymentProcessor {

    ProcessPaymentResponse processCardPayment(ProcessCardPaymentRequest processCardPaymentRequest);
}

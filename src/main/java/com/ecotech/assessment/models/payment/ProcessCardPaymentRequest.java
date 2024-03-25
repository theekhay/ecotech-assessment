package com.ecotech.assessment.models.payment;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder()
public class ProcessCardPaymentRequest {

    private BigDecimal amount;
    private String traceId;

    //other properties here
}

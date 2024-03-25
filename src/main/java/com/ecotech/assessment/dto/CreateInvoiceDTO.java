package com.ecotech.assessment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateInvoiceDTO {

    private BigDecimal amount;
}

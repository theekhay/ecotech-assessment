package com.ecotech.assessment.models;


import com.ecotech.assessment.enums.PaymentChannel;
import com.ecotech.assessment.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@RequiredArgsConstructor
@Entity()
@Data()
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column()
    private BigDecimal amount;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentChannel channel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private String processor;

    @Column(columnDefinition = "default PENDING")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column( nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(nullable = true)
    private String paymentRequest;

    @Column(nullable = true)
    private String paymentResponse;
}

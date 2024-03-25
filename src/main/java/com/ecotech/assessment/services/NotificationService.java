package com.ecotech.assessment.services;


import com.ecotech.assessment.interfaces.IEmailProvider;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements IEmailProvider {

    private IEmailProvider iEmailProvider;

    @Override
    public void sendInvoice(String email, String invoiceId) {
        iEmailProvider.sendInvoice(email, invoiceId);
    }
}

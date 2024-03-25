package com.ecotech.assessment.providers;


import com.ecotech.assessment.interfaces.IEmailProvider;
import org.springframework.stereotype.Service;

@Service
public class Sendgrid implements IEmailProvider {

    @Override
    public void sendInvoice(String email, String invoiceId) {
        //email implementation for sending emails here
    }
}

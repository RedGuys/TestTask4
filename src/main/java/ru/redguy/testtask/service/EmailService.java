package ru.redguy.testtask.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EmailService {
    @Autowired
    private ProducerTemplate producerTemplate;

    @Value("${camel.component.mail.host}")
    private String host;

    public void sendEmail(String email, String subject, String text) {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("To", email);
        headers.put("CamelSmtpTo", email);
        headers.put("CamelSmtpSubject", subject);
        headers.put("CamelCharsetName", "UTF-8");
        producerTemplate.sendBodyAndHeaders(host, text, headers);
    }
}

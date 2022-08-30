package com.dobudobu.perpustakaan.Service;

public interface EmailSenderService {

    void sendEmail(String to, String subject, String body);
}

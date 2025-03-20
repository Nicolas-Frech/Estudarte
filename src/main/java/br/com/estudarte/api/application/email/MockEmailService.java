package br.com.estudarte.api.application.email;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Profile({"dev", "default"})
public class MockEmailService implements EmailSender {
    @Override
    public void enviarEmail(String to, String subject, String message) {
        System.out.println("📧 [MOCK EMAIL] Para: " + to + ", Assunto: " + subject);
        System.out.println("Mensagem: " + message);
    }

    @Override
    public String formatarData(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return data.format(formatter);
    }
}

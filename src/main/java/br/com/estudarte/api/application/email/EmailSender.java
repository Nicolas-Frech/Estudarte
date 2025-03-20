package br.com.estudarte.api.application.email;

import java.time.LocalDateTime;

public interface EmailSender {

    void enviarEmail(String to, String subject, String message);

    String formatarData(LocalDateTime data);
}

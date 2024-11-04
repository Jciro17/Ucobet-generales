package co.edu.uco.UcoBet.generales.infraestructure.primaryadapters.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.UcoBet.generales.application.primaryports.dto.EmailDataDto;
import co.edu.uco.UcoBet.generales.application.secondaryports.notificationservice.NotificationService;

@RestController
@RequestMapping("/api/v1/email")
public class SendEmailController {

    private final NotificationService notificationService;

    public SendEmailController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDataDto emailDataDto) {
        try {
            notificationService.send(emailDataDto.getTo(), emailDataDto.getSubject(), emailDataDto.getContent());
            return ResponseEntity.status(HttpStatus.OK).body("Correo enviado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enviando correo.");
        }
    }
}




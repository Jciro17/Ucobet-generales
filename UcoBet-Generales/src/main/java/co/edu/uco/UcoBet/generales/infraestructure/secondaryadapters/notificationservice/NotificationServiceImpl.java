package co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.notificationservice;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import co.edu.uco.UcoBet.generales.application.secondaryports.notificationservice.NotificationService;
import co.edu.uco.UcoBet.generales.crosscutting.exceptions.SendgridUcoBetException;

@Component
public class NotificationServiceImpl implements NotificationService {

    private final String apiKey = "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";

    @Override
    public void send(String to, String subject, String content) {
        Email from = new Email("cirojeronimo17@gmail.com");
        Email toEmail = new Email(to);
        Content emailContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, toEmail, emailContent);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

        } catch (IOException exception) {
            throw SendgridUcoBetException.create("Error al enviar el correo");
        }
    }
}

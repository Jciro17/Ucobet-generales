package co.edu.uco.UcoBet.generales.application.primaryports.interactor.email.impl;

import org.springframework.stereotype.Service;

import co.edu.uco.UcoBet.generales.application.primaryports.dto.EmailDataDto;
import co.edu.uco.UcoBet.generales.application.primaryports.interactor.email.SendEmailInteractor;
import co.edu.uco.UcoBet.generales.application.primaryports.mapper.EmailDataMapper;
import co.edu.uco.UcoBet.generales.application.usecase.email.SendEmail;
import co.edu.uco.UcoBet.generales.domain.EmailData;

@Service
public class SendEmailInteractorImpl implements SendEmailInteractor {

    private final SendEmail sendEmail;

    public SendEmailInteractorImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void execute(EmailDataDto emailDataDTO) {
        // Usando la instancia estática del mapper
        EmailData emailData = EmailDataMapper.INSTANCE.toDomain(emailDataDTO);
        // Llamada al caso de uso
        sendEmail.execute(emailData);
    }
}
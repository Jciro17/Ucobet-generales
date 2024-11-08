package co.edu.uco.ucobet.generales.application.usecase.city.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.ucobet.generales.application.primaryports.dto.email.EmailMessage;
import co.edu.uco.ucobet.generales.application.secondaryports.entity.CityEntity;
import co.edu.uco.ucobet.generales.application.secondaryports.mapper.StateEntityMapper;
import co.edu.uco.ucobet.generales.application.secondaryports.notificationservice.NotificationService;
import co.edu.uco.ucobet.generales.application.secondaryports.repository.CityRepository;
import co.edu.uco.ucobet.generales.application.usecase.city.RegisterNewCity;
import co.edu.uco.ucobet.generales.application.usecase.city.RegisterNewCityRuleValidator;
import co.edu.uco.ucobet.generales.crosscutting.helpers.UUIDHelper;
import co.edu.uco.ucobet.generales.domain.city.CityDomain;
import co.edu.uco.ucobet.generales.infraestructure.secondaryadapters.redis.MessageCatalogServiceImpl;
import co.edu.uco.ucobet.generales.infraestructure.secondaryadapters.redis.ParameterCatalogServiceImpl;

@Service
public final class RegisterNewCityImpl implements RegisterNewCity {

    private final CityRepository cityRepository;
    private final RegisterNewCityRuleValidator registerNewCityRuleValidator;
    private final NotificationService notificationService;
    private final MessageCatalogServiceImpl messageCatalogService;
    private final ParameterCatalogServiceImpl parameterCatalogService;

    public RegisterNewCityImpl(CityRepository cityRepository, RegisterNewCityRuleValidator registerNewCityRuleValidator,
                               NotificationService notificationService, MessageCatalogServiceImpl messageCatalogService,
                               ParameterCatalogServiceImpl parameterCatalogService) {
        this.cityRepository = cityRepository;
        this.registerNewCityRuleValidator = registerNewCityRuleValidator;
        this.notificationService = notificationService;
        this.messageCatalogService = messageCatalogService;
        this.parameterCatalogService = parameterCatalogService;
    }

    @Override
    public void execute(final CityDomain data) {
        // Validar reglas de negocio
        registerNewCityRuleValidator.validate(data);

        var id = generarIdentificadorCiudad();

        // Mapper de domain a entity
        var cityEntity = CityEntity.create().setId(id).setName(data.getName())
                                   .setState(StateEntityMapper.INSTANCE.toEntity(data.getState()));

        // Registrar la ciudad
        cityRepository.save(cityEntity);
        // Obtener los valores necesarios desde los servicios
        String subject = messageCatalogService.getMessage("asuntoCorreo");
        String content = messageCatalogService.getMessage("ciudadExitosa");

        // Obtener el correo del administrador desde el servicio de parámetros
        String adminEmail = "miguelangeljaramilloarenas6@gmail.com";

        // Crear el mensaje de correo
        EmailMessage emailMessage = new EmailMessage(adminEmail, subject, content);

        // Enviar notificación usando el objeto EmailMessage
        notificationService.send(emailMessage);
    }

    private UUID generarIdentificadorCiudad() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var resultados = cityRepository.findById(id);
            existeId = !resultados.isEmpty();
        }
        return id;
    }
}

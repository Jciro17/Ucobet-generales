package co.edu.uco.UcoBet.generales.application.usecase.city.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.UcoBet.generales.application.secondaryports.entity.CityEntity;
import co.edu.uco.UcoBet.generales.application.secondaryports.mapper.StateEntityMapper;
import co.edu.uco.UcoBet.generales.application.secondaryports.notificationservice.NotificationService;
import co.edu.uco.UcoBet.generales.application.secondaryports.repository.CityRepository;
import co.edu.uco.UcoBet.generales.application.usecase.city.RegisterNewCity;
import co.edu.uco.UcoBet.generales.application.usecase.city.RegisterNewCityRuleValidator;
import co.edu.uco.UcoBet.generales.crosscutting.helpers.UUIDHelper;
import co.edu.uco.UcoBet.generales.domain.city.CityDomain;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogServiceImpl;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.ParameterCatalogServiceImpl;

@Service
public final class RegisterNewCityImpl implements RegisterNewCity {

    private final CityRepository cityRepository;
    private final RegisterNewCityRuleValidator registerNewCityRuleValidator;
    private final NotificationService notificationService;
    private final MessageCatalogServiceImpl messageCatalogService;
    private final ParameterCatalogServiceImpl parameterCatalogService; // Nuevo servicio de parámetros

    public RegisterNewCityImpl(CityRepository cityRepository, RegisterNewCityRuleValidator registerNewCityRuleValidator,
                               NotificationService notificationService, MessageCatalogServiceImpl messageCatalogService,
                               ParameterCatalogServiceImpl parameterCatalogService) {
        this.cityRepository = cityRepository;
        this.registerNewCityRuleValidator = registerNewCityRuleValidator;
        this.notificationService = notificationService;
        this.messageCatalogService = messageCatalogService;
        this.parameterCatalogService = parameterCatalogService; // Inyección del servicio de parámetros
    }

    @Override
    public void execute(final CityDomain data) {
        // Validar reglas de negocio
        registerNewCityRuleValidator.validate(data);

        var id = generarIdentificadorCiudad();
        System.out.println(id);

        // Mapper de domain a entity
        var cityEntity = CityEntity.create().setId(id).setName(data.getName())
                                   .setState(StateEntityMapper.INSTANCE.toEntity(data.getState()));

        // Registrar la ciudad
        cityRepository.save(cityEntity);

        String subject = messageCatalogService.getMessage("asuntoCorreo");
        String content = messageCatalogService.getMessage("ciudadExitosa");
        
        // Obtener el correo del administrador desde el servicio de parámetros
        String adminEmail = parameterCatalogService.getParameter("correo");

        notificationService.send(adminEmail, subject, content); // Ahora usando el correo desde el servicio de parámetros
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

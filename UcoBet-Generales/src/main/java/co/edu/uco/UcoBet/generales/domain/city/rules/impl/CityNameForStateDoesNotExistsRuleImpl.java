package co.edu.uco.UcoBet.generales.domain.city.rules.impl;

import org.springframework.stereotype.Service;

import co.edu.uco.UcoBet.generales.application.secondaryports.entity.CityEntity;
import co.edu.uco.UcoBet.generales.application.secondaryports.mapper.StateEntityMapper;
import co.edu.uco.UcoBet.generales.application.secondaryports.repository.CityRepository;
import co.edu.uco.UcoBet.generales.domain.city.CityDomain;
import co.edu.uco.UcoBet.generales.domain.city.exception.CityNameForStateDoesExistsException;
import co.edu.uco.UcoBet.generales.domain.city.rules.CityNameForStateDoesNotExistsRule;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogService;

@Service
public class CityNameForStateDoesNotExistsRuleImpl implements CityNameForStateDoesNotExistsRule {

	private CityRepository cityRepository;
	
	private MessageCatalogService messageCatalogService;

	public CityNameForStateDoesNotExistsRuleImpl(CityRepository cityRepository,MessageCatalogService messageCatalogService) {
		this.cityRepository = cityRepository;
		this.messageCatalogService=messageCatalogService;
	}

	@Override
	public void execute(CityDomain data) {
		var cityEntity = CityEntity.create().setName(data.getName())
				.setState(StateEntityMapper.INSTANCE.toEntity(data.getState()));

		var resultado = cityRepository.findByFilter(cityEntity);

		if (!resultado.isEmpty()) {
			throw CityNameForStateDoesExistsException.create(messageCatalogService);
		}

	}

}

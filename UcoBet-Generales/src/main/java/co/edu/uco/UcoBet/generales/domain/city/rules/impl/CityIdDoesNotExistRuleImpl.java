package co.edu.uco.ucobet.generales.domain.city.rules.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.ucobet.generales.application.secondaryports.repository.CityRepository;
import co.edu.uco.ucobet.generales.domain.city.exception.CityIdDoesExistsException;
import co.edu.uco.ucobet.generales.domain.city.rules.CityIdDoesNotExistRule;
import co.edu.uco.ucobet.generales.infraestructure.secondaryadapters.redis.MessageCatalogServiceImpl;

@Service
public class CityIdDoesNotExistRuleImpl implements CityIdDoesNotExistRule {
	private CityRepository cityRepository;

	private MessageCatalogServiceImpl messageCatalogService;

	public CityIdDoesNotExistRuleImpl(CityRepository cityRepository, MessageCatalogServiceImpl messageCatalogService) {
		this.cityRepository = cityRepository;
		this.messageCatalogService = messageCatalogService;
	}

	@Override
	public void execute(UUID data) {
		if (cityRepository.existsById(data)) {
			throw CityIdDoesExistsException.create(messageCatalogService);
		}

	}
}

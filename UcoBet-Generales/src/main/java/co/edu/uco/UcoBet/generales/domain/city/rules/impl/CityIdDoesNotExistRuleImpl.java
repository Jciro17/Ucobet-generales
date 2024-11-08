package co.edu.uco.UcoBet.generales.domain.city.rules.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.UcoBet.generales.application.secondaryports.repository.CityRepository;
import co.edu.uco.UcoBet.generales.domain.city.exception.CityIdDoesExistsException;
import co.edu.uco.UcoBet.generales.domain.city.rules.CityIdDoesNotExistRule;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogService;
@Service
public class CityIdDoesNotExistRuleImpl implements CityIdDoesNotExistRule{
	@Autowired
	private CityRepository cityRepository;
	

    @Autowired
    private MessageCatalogService messageCatalogService; 

	

	public CityIdDoesNotExistRuleImpl(final CityRepository cityRepository) {
		this.cityRepository= cityRepository;
	}
	
	
	@Override
	public void execute(UUID data) {
		if (cityRepository.existsById(data)){
			throw CityIdDoesExistsException.create(messageCatalogService);
		}
		
	}
}

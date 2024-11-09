package co.edu.uco.ucobet.generales.domain.city.rules.impl;

import org.springframework.stereotype.Service;

import co.edu.uco.ucobet.generales.crosscutting.helpers.TextHelper;
import co.edu.uco.ucobet.generales.domain.city.exception.CityNameIsEmptyExcpetion;
import co.edu.uco.ucobet.generales.domain.city.rules.CityNameIsNotEmpyRule;
import co.edu.uco.ucobet.generales.infraestructure.secondaryadapters.redis.MessageCatalogServiceImpl;


@Service
public class CityNameIsNotEmpyRuleImpl implements CityNameIsNotEmpyRule{
	
	private MessageCatalogServiceImpl messageCatalogService;
	
	

	public CityNameIsNotEmpyRuleImpl(MessageCatalogServiceImpl messageCatalogService) {
		this.messageCatalogService = messageCatalogService;
	}



	@Override
	public void execute(String data) {
		if(TextHelper.isEmpty(data)) {
			throw CityNameIsEmptyExcpetion.create(messageCatalogService);
		}
		
	}

}

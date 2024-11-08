package co.edu.uco.UcoBet.generales.domain.city.rules.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.UcoBet.generales.crosscutting.helpers.TextHelper;
import co.edu.uco.UcoBet.generales.domain.city.exception.CityNameIsNullException;
import co.edu.uco.UcoBet.generales.domain.city.rules.CityNameIsNotNullRule;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogService;


@Service
public class CityNameIsNotNullRuleImpl implements CityNameIsNotNullRule {
	
	@Autowired
	private MessageCatalogService messageCatalogService;

	@Override
	public void execute(String data) {
		if(TextHelper.isNull(data)) {
			throw CityNameIsNullException.create(messageCatalogService);
		}
		
	}

}

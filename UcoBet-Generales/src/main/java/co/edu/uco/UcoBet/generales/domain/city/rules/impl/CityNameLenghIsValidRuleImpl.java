package co.edu.uco.UcoBet.generales.domain.city.rules.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.UcoBet.generales.domain.city.exception.CityNameLenghIsNotValidException;
import co.edu.uco.UcoBet.generales.domain.city.rules.CityNameLenghIsValidRule;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogService;


@Service
public class CityNameLenghIsValidRuleImpl implements CityNameLenghIsValidRule{
	
	@Autowired
	private MessageCatalogService messageCatalogService;
	
	private static final int MIN_NAME_LENGTH = 5;
	private static final int MAX_NAME_LENGTH = 40;

	@Override
	public void execute(String data) {
		if (data.length() < MIN_NAME_LENGTH || data.length() > MAX_NAME_LENGTH) {
			throw CityNameLenghIsNotValidException.create(messageCatalogService);
		
	}
	}

}

package co.edu.uco.UcoBet.generales.domain.city.rules.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.UcoBet.generales.crosscutting.helpers.ObjectHelper;
import co.edu.uco.UcoBet.generales.domain.city.exception.CityIdIsEmptyException;
import co.edu.uco.UcoBet.generales.domain.city.rules.CityIdIsNotEmptyRule;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogService;
@Service
public class CityIdIsNotEmptyRuleImpl implements CityIdIsNotEmptyRule {
	
	@Autowired
	private MessageCatalogService messageCatalogService;

	@Override
	public final void execute(final UUID data) {
		if(ObjectHelper.isNull(data)) {
			throw CityIdIsEmptyException.create(messageCatalogService);
		}
		
	}

}

package co.edu.uco.UcoBet.generales.domain.city.rules.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.UcoBet.generales.domain.city.exception.CityNameFormatIsNotValidException;
import co.edu.uco.UcoBet.generales.domain.city.rules.CityNameFormatIsValidRule;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogService;


@Service
public class CityNameFormatIsValidRuleImpl implements CityNameFormatIsValidRule {
	
	@Autowired
	private MessageCatalogService messageCatalogService;

    @Override
    public void execute(String data) {
        if (!data.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw CityNameFormatIsNotValidException.create(messageCatalogService);
        }
    }
}

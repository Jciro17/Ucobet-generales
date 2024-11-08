package co.edu.uco.UcoBet.generales.domain.city.exception;

import co.edu.uco.UcoBet.generales.crosscutting.exceptions.RuleUcoBetException;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogService;

public class CityNameIsEmptyExcpetion extends RuleUcoBetException {


	private static final long serialVersionUID = 1L;

	public CityNameIsEmptyExcpetion(String technicalMessage, String userMessage, Exception rootException) {
		super(technicalMessage, userMessage, rootException);
		// TODO Auto-generated constructor stub
	}
	
	public static final CityNameIsEmptyExcpetion create(MessageCatalogService messageCatalogService) {
		var userMessage = messageCatalogService.getMessage("CityNameIsEmptyExcpetion");
		return new CityNameIsEmptyExcpetion(userMessage, userMessage, new Exception());
	}

}

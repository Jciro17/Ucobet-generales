package co.edu.uco.UcoBet.generales.domain.city.exception;

import co.edu.uco.UcoBet.generales.crosscutting.exceptions.RuleUcoBetException;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogService;

public class CityIdIsEmptyException extends RuleUcoBetException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CityIdIsEmptyException(String technicalMessage, String userMessage, Exception rootException) {
		super(technicalMessage, userMessage, rootException);
		// TODO Auto-generated constructor stub
	}
	
	public static final CityIdIsEmptyException create(MessageCatalogService messageCatalogService) {
		var userMessage= messageCatalogService.getMessage("CityIdIsEmptyException");
		return new CityIdIsEmptyException(userMessage, userMessage, new Exception());
	}

}

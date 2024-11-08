package co.edu.uco.UcoBet.generales.domain.city.exception;

import co.edu.uco.UcoBet.generales.crosscutting.exceptions.RuleUcoBetException;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogServiceImpl;

public class CityStateIsNotValidException extends RuleUcoBetException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CityStateIsNotValidException(String technicalMessage, String userMessage, Exception rootException) {
		super(technicalMessage, userMessage, rootException);
		// TODO Auto-generated constructor stub
	}
	
	public static final CityStateIsNotValidException create(MessageCatalogServiceImpl messageCatalogService) {
		var userMessage = messageCatalogService.getMessage("CityStateIsNotValidException");
		return new CityStateIsNotValidException(userMessage, userMessage, new Exception());
	}
}

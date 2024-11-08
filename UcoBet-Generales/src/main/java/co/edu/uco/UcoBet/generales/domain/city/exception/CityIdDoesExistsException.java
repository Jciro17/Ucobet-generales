package co.edu.uco.ucobet.generales.domain.city.exception;




import co.edu.uco.ucobet.generales.crosscutting.exceptions.RuleUcoBetException;
import co.edu.uco.ucobet.generales.infraestructure.secondaryadapters.redis.MessageCatalogServiceImpl;


public class CityIdDoesExistsException extends RuleUcoBetException {

	private static final long serialVersionUID = 1L;

	

	public CityIdDoesExistsException(String technicalMessage, String userMessage, Exception rootException) {
		super(technicalMessage, userMessage, rootException);
		// TODO Auto-generated constructor stub
	}
	
	
	public static final CityIdDoesExistsException create(MessageCatalogServiceImpl messageCatalogService) {
		var userMessage = messageCatalogService.getMessage("CityIdDoesExistsException");
		return new CityIdDoesExistsException(userMessage, userMessage, new Exception());
		
	}

}

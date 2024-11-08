package co.edu.uco.UcoBet.generales.domain.city.exception;




import co.edu.uco.UcoBet.generales.crosscutting.exceptions.RuleUcoBetException;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogServiceImpl;


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

package co.edu.uco.ucobet.generales.infraestructure.secondaryadapters.sanitizer;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.ucobet.generales.infraestructure.secondaryadapters.redis.MessageCatalogServiceImpl;

@Service
public class SanitizerService {
	
	@Autowired
	private MessageCatalogServiceImpl messageCatalogService;
    private static final Logger logger = LoggerFactory.getLogger(SanitizerService.class);
    private final PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

    public String sanitize(String input) {
        if (input == null) {
            logger.warn(messageCatalogService.getMessage("sanitizer"));
            return null; // o considera retornar una cadena vacía
        }

        String sanitizedInput = policy.sanitize(input);
        logger.debug("Entrada sanitizada: {}", sanitizedInput); // Registra la salida sanitizada
        return sanitizedInput;
    }


}

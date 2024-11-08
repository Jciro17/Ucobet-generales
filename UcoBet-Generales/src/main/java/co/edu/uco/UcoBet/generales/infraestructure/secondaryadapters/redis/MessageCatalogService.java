package co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageCatalogService {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	// Método para obtener un mensaje del catálogo
	public String getMessage(String key) {
		return redisTemplate.opsForValue().get(key);
	}

}

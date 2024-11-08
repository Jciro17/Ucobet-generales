package co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ParameterCatalogServiceImpl {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // Método para obtener un parámetro de configuración
    public String getParameter(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

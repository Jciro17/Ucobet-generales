package co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import co.edu.uco.UcoBet.generales.application.secondaryports.redis.MessageCatalogService;


@Component
public class MessageCatalogServiceImpl implements MessageCatalogService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String getMessage(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

package ps.zhao.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(1)
public class Main implements CommandLineRunner {

    @Autowired
    RedisTemplate redisTemplate = null;

    public void p(){
        redisTemplate.opsForValue().set("name", "zhaohs");
        log.debug("set value to redis ...");

        String name = redisTemplate.opsForValue().get("name").toString();
        log.debug("get name from redis: {}", name);
    }

    @Override
    public void run(String... strings) throws Exception {
        p();
    }
}

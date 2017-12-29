package ps.zhao.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class Main implements CommandLineRunner {

    @Value(value="${redis.host}")
    private String host = null;
    @Value(value="${redis.port}")
    private int port = 0;
    @Value(value="${redis.password}")
    private String password = null;

    @Autowired
    RedisTemplate redisTemplate = null;


    @Bean
    public RedisTemplate<String, String> redisTemplate (JedisConnectionFactory connectionFactory){
        RedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    public JedisConnectionFactory connectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setTimeout(3000);
        return factory;
    }

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

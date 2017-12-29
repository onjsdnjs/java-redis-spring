package ps.zhao.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import ps.zhao.demo.beans.Person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
public class Main2 implements CommandLineRunner {

    @Value(value = "${redis.host}")
    private String host = null;
    @Value(value = "${redis.port}")
    private int port = 0;
    @Value(value = "${redis.password}")
    private String password = null;

    @Autowired
    @Qualifier("rt")
    private RedisTemplate redisTemplate = null;

    @Bean(name = "rt")
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setTimeout(3000);
        return factory;
    }

    /**
     * redis中的保存结果为
     * <p>
     * > get person
     * > "{\"@class\":\"ps.zhao.demo.beans.Person\",\"name\":\"zhaohs\",\"age\":34}"
     */
    public void p() {
        Person p = new Person("zhaohs", 34);

        redisTemplate.opsForValue().set("person", p);
        log.debug("set value to redis ...");

        Person person = (Person) redisTemplate.opsForValue().get("person");
        log.debug("get name from redis: {}", person.toString());

        System.exit(0);

    }

    /**
     * 对象的List存储在redis中
     * > get person_llist
     * > "[\"java.util.ArrayList\",[{\"@class\":\"ps.zhao.demo.beans.Person\",\"name\":\"name_11\",\"age\":1},{\"@class\":\"ps.zhao.demo.beans.Person\",\"name\":\"name_12\",\"age\":2},{\"@class\":\"ps.zhao.demo.beans.Person\",\"name\":\"name_13\",\"age\":3}]]"
     */
    public void p2() {

        List<Person> personList = IntStream.range(1, 4).mapToObj(Integer::new).map(i -> {
            return new Person("name_1" + i.intValue(), i.intValue());
        }).collect(Collectors.toList());


        redisTemplate.opsForValue().set("person_list", personList);
        log.debug("set value to redis ...");

        List<Person> getPersonList = (List<Person>) redisTemplate.opsForValue().get("person_list");
        log.debug("get person list from redis, size is: {}", getPersonList.size());

        System.exit(0);

    }

    @Override
    public void run(String... strings) throws Exception {
        p2();
    }
}

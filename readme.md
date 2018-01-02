* Spring Boot + Redis

    * Spring Boot 操作 Redis是通过 RedisTemplate 来进行的
    
    * Spring Boot 提供了，key value都是String形的 RedisTemplate： StringRedisTemplate
    
    * [Main.java](src/main/java/ps/zhao/demo/Main.java)
    
        * 手动将数据存储进入redis
        * 使用 RedisTemplate<String, String> 向redis 写入 / 读取
        
    * [Main2.java](src/main/java/ps/zhao/demo/Main2.java)
        
        * 手动将数据存储进入redis
        * 使用 RedisTemplate<String, Object> 向redis 写入 / 读取
        * 写入对象
        * 写入对象List
        
    * [Main3.java](src/main/java/ps/zhao/demo/Main2.java)
        
        * 自动将数据存储进入redis
        * 使用 `@Cacheable` 注解来实现
        
* 更多参考 & Spring Boot + web + redis的实现

    * [spring-boot-boilerplate](https://github.com/yisuren/spring-boot-boilerplate)
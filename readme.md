* Spring Boot + Redis

    * Spring Boot 操作 Redis是通过 RedisTemplate 来进行的
    
    * Spring Boot 提供了，key value都是String形的 RedisTemplate： StringRedisTemplate
    
    * [Main.java](src/main/java/ps/zhao/demo/Main.java)
    
        * 使用 RedisTemplate<String, String> 向redis 写入 / 读取
        
    * [Main2.java](src/main/java/ps/zhao/demo/Main2.java)
        
        * 使用 RedisTemplate<String, Object> 向redis 写入 / 读取
        * 写入对象
        * 写入对象List
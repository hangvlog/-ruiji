package com.example.ruiji;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RuijiApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @org.junit.Test
    @Test
    public void testString() {
        stringRedisTemplate.opsForValue().set("name","hang");

    }

}

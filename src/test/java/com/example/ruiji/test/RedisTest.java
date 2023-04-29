package com.example.ruiji.test;

import com.example.ruiji.ReggieApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Enzo Cotter on 2023/4/22.
 */

//@SpringBootTest
@SpringBootTest(classes = ReggieApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest { //    @Autowired
    //   private RedisTemplate redisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testString() {
        stringRedisTemplate.opsForValue().set("name","hang");

    }
}

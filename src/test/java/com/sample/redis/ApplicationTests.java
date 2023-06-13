package com.sample.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guozhiyang@sensetime.com
 * @Description: todo
 * @date 2023-06-09 10:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        String t_w_d_temp = stringRedisTemplate.opsForValue().get("T_W_D_TEMP");
        System.out.println(t_w_d_temp);
    }
}

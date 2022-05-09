package com.example.real_store.mapper;

import com.example.real_store.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class Redis {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("uid","1233");

        String uid = stringRedisTemplate.opsForValue().get("uid");

        System.out.println(uid);
    }

    private static final ObjectMapper mapper=new ObjectMapper();

    @Test
    public void insertClass() throws Exception{
        User user = new User();

        user.setUsername("123");
        user.setUid(123);

        String s = mapper.writeValueAsString(user);

        stringRedisTemplate.opsForValue().set("user",s);

        String user1 = stringRedisTemplate.opsForValue().get("user");

        User user2 = mapper.readValue(user1, User.class);

        System.out.println(user2);


    }


    @Test
    public void hashTest(){

        stringRedisTemplate.opsForHash().put("one","名字","向康蕾");
        stringRedisTemplate.opsForHash().put("one","大学","中央民族大学");
        stringRedisTemplate.opsForHash().put("one","专业","哲学与宗教学");
        stringRedisTemplate.opsForHash().put("one","原因","我感觉高中挺对不起她的，很想再见她一面，" +
                "哪怕之说一声抱歉也好，当然也有可能实我自作多情，或许她根本就不想回忆起我吧，我不应该去打扰她" +
                "世界真的很大很大啊，大到我们一旦毕业，就是两个世界的人，或许再也不会产生交集"+
                "曾经我没有自信和勇气去接受她的爱意，当我有勇气接受的时候，才发现早就已经错过，可能有的人注定有缘无份，人只有失去后才会明白，，可悲，哈哈哈哈哈哈哈哈，挺好笑的，不是吗？"+
                "希望她一切安好。");

        Map<Object, Object> one = stringRedisTemplate.opsForHash().entries("one");

        System.out.println(one);



    }



}

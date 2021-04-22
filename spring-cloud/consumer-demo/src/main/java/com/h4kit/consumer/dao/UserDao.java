package com.h4kit.consumer.dao;

import com.h4kit.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserDao {

    @Autowired
    private RestTemplate restTemplate;

    //@HystrixCommand(fallbackMethod = "queryUserByIdFallback")
    public User queryUserById(Long id){
        String url = "http://user-service/user/" + id;
        return this.restTemplate.getForObject(url, User.class);
    }

    public User queryUserByIdFallback(Long id){
        User user = new User();
        user.setId(id);
        user.setName("用户信息查询出现异常！");
        return user;
    }
}
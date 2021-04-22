package com.h4kit.consumer.service;

import com.h4kit.consumer.client.UserFeignClient;
import com.h4kit.consumer.dao.UserDao;
import com.h4kit.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserFeignClient userFeignClient;

    public List<User> queryUserByIds(List<Long> ids){
        List<User> users = new ArrayList<>();
        // 地址直接写服务名称即可
        String baseUrl = "http://user-service/user/";
        ids.forEach(id -> {
            // 我们测试多次查询，
            users.add(this.restTemplate.getForObject(baseUrl + id, User.class));
            // 每次间隔500毫秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return users;
    }

    public User queryUserById(Long id) throws InterruptedException {
        return this.userDao.queryUserById(id);

    }

    public List<User> findById(List<Long> ids) throws InterruptedException {
        List<User> users = new ArrayList<>();
        for (Long id : ids) {
//            Thread.sleep(new Random().nextInt(2000));
            users.add(userFeignClient.queryById(id));
        }
        return users;
    }
}
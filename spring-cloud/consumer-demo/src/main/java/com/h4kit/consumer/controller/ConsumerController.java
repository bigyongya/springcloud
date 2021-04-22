package com.h4kit.consumer.controller;

import com.h4kit.consumer.pojo.User;
import com.h4kit.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("consume")
public class ConsumerController {

    @Autowired
    private UserService userService;

    @GetMapping("findByIds")
    public List<User> consume(@RequestParam("ids") List<Long> ids) {
        return this.userService.queryUserByIds(ids);
    }

    @GetMapping("findById")
    public User findById(@RequestParam("id") Long id) throws InterruptedException {
        return this.userService.queryUserById(id);
    }

    @GetMapping("queryByIds")
    public List<User> queryById(@RequestParam("ids") List<Long> id) throws InterruptedException {
        return this.userService.findById(id);
    }
}
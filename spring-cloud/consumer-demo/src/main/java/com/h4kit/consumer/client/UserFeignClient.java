package com.h4kit.consumer.client;

import com.h4kit.consumer.fallback.UserFeignClientFallback;
import com.h4kit.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lenovo
 * @date 2021/4/21 11:12
 */
@FeignClient(name = "user-service",fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    User queryById(@PathVariable("id") Long id);
}

package com.h4kit.consumer.fallback;

import com.h4kit.consumer.client.UserFeignClient;
import com.h4kit.consumer.pojo.User;
import org.springframework.stereotype.Component;

/**

 * @author lenovo
 * @date 2021/4/21 11:19
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("用户查询出现异常！");
        return user;
    }
}

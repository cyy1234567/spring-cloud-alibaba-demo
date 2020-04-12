package com.example.democlient.FeignClient;

import org.springframework.stereotype.Component;

@Component
public class FeignClientFallBack implements DemoServerFeignClient{

    @Override
    public User getUser(User user) {
        User user1 = new User();
        user1.setName("error");
        return user1;
    }
}

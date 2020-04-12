package com.example.democlient.FeignClient;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignClientFallBackFactory implements FallbackFactory<DemoServerFeignClient> {
    @Override
    public DemoServerFeignClient create(Throwable throwable) {
        throwable.printStackTrace();
        return new DemoServerFeignClient(){
            @Override
            public User getUser(User user) {
                User user1 = new User();
                user1.setName("error-FallBackFactory");
                return user1;
            }
        };
    }
}

package com.example.democlient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestContextHolder;

@SpringBootTest
class DemoClientApplicationTests {

    @Test
    void contextLoads() {
        RequestContextHolder.getRequestAttributes();
    }

}

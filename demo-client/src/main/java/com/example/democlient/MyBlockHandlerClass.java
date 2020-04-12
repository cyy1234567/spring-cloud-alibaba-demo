package com.example.democlient;



import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class MyBlockHandlerClass {
    /**
     * 限流后处理方法
     */
    public static ClientHttpResponse intercept(HttpRequest var1, byte[] var2, ClientHttpRequestExecution var3,BlockException e) throws IOException{
       //System.err.println("block: " + ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse("{\"msg\":\"error\"}");
    }
}

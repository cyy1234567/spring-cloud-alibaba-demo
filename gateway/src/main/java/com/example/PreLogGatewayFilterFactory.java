package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class PreLogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {

        return ((exchange,chain) -> {
            //所有的逻辑都应该写在里面，只有这里能拿到请求对象
            System.out.println("请求进来了。。。。。。。。。。。。。。。。"+config.getName()+"---------"+config.getValue());
            ServerHttpRequest request = exchange
                    .getRequest()
                    .mutate()
                    //修改request
                    .build();
            ServerWebExchange modifyExchange =exchange
                    .mutate()
                    .request(request)  //让修改的request生效
                    .build();

            return chain.filter(modifyExchange);
        });
    }
}

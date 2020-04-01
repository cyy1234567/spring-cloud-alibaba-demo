package com.example.democlient.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;

public class NacosWeightedRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        //读取配置文件，并初始化，一般用不上留空
        //实际上，Ribbon中的大部分负载均衡实现都是留空
    }

    @Override
    public Server choose(Object o) {
        try {
            //ribbon的入口
            //ILoadBalancer iLoadBalancer  =this.getLoadBalancer();
            BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) this.getLoadBalancer();

            //目标微服务的名称
            String name = baseLoadBalancer.getName();

            //使用nacos-client提供的基于权重的负载均衡算法
            //1、得到服务发现的相关api
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();

            //2、基于权重得到一个api
            Instance instance=namingService.selectOneHealthyInstance(name);


            return new NacosServer(instance);
        }catch (NacosException e){
            e.printStackTrace();
        }
        return null;
    }
}

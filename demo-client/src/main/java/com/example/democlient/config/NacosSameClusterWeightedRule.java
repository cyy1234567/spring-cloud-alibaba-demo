package com.example.democlient.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancer;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NacosSameClusterWeightedRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        try {
            //集群名称
            String clusterName = nacosDiscoveryProperties.getClusterName();

            BaseLoadBalancer loadBalancer = (BaseLoadBalancer)this.getLoadBalancer();
            //目标服务名称
            String serviceName = loadBalancer.getName();

            //得到服务发现的相关api
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();


            //1、找到指定服务的所有实例 AList
            List<Instance > aList = namingService.selectInstances(serviceName,true);

            //2、过滤出相同集群下的所有实例 BList
            List<Instance > bList = aList.stream().filter(instance -> {
                        return Objects.equals(instance.getClusterName(),clusterName);
                    }
            ).collect(Collectors.toList());
            //3、如果BList是空 用AList
            List<Instance > cList = null;
            if(!CollectionUtils.isEmpty(bList)){
                cList = bList;
            }else{
                cList = aList;
            }
            //4、基于权重返回一个实例
            Instance instance =  ExtendBalancer.getHostByRandomWeight2(cList);
            return new NacosServer(instance);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}

//继承别人的类，扩展访问级别
class ExtendBalancer extends Balancer{
    public static Instance getHostByRandomWeight2(List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }
}


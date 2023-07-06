package com.genug.proxy.config.v2_proxy;

import com.genug.proxy.app.v2.OrderControllerV2;
import com.genug.proxy.app.v2.OrderRepositoryV2;
import com.genug.proxy.app.v2.OrderServiceV2;
import com.genug.proxy.config.v2_proxy.concrete_proxy.OrderControllerConcreteProxy;
import com.genug.proxy.config.v2_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import com.genug.proxy.config.v2_proxy.concrete_proxy.OrderServiceConcreteProxy;
import com.genug.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV2 orderController(LogTrace logTrace) {
        OrderControllerV2 orderController = new OrderControllerV2(orderService(logTrace));
        return new OrderControllerConcreteProxy(orderController, logTrace);
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace logTrace) {
        OrderServiceV2 orderService = new OrderServiceV2(orderRepository(logTrace));
        return new OrderServiceConcreteProxy(orderService, logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
        OrderRepositoryV2 orderRepository = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(orderRepository, logTrace);
    }
}

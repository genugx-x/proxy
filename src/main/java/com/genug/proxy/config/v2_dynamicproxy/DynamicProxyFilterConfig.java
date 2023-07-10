package com.genug.proxy.config.v2_dynamicproxy;

import com.genug.proxy.app.v1.*;
import com.genug.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import com.genug.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import com.genug.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));
        return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                LogTraceFilterHandler.builder()
                        .target(orderController)
                        .logTrace(logTrace)
                        .patterns(PATTERNS)
                        .build());
                // new LogTraceBasicHandler(orderController, logTrace));
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                LogTraceFilterHandler.builder()
                        .target(orderService)
                        .logTrace(logTrace)
                        .patterns(PATTERNS)
                        .build());
                // new LogTraceBasicHandler(orderService, logTrace));
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        return (OrderRepositoryV1) Proxy.newProxyInstance(
                orderRepository.getClass().getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                LogTraceFilterHandler.builder()
                        .target(orderRepository)
                        .logTrace(logTrace)
                        .patterns(PATTERNS)
                        .build());
                // new LogTraceBasicHandler(orderRepository, logTrace));
    }
}

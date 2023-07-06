package com.genug.proxy.config.v2_proxy.concrete_proxy;

import com.genug.proxy.app.v2.OrderRepositoryV2;
import com.genug.proxy.trace.TraceStatus;
import com.genug.proxy.trace.logtrace.LogTrace;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {
    private final OrderRepositoryV2 target;
    private final LogTrace logTrace;

    public OrderRepositoryConcreteProxy(OrderRepositoryV2 target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.save()");
            target.save(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}

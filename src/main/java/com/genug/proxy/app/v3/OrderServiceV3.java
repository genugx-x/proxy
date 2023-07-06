package com.genug.proxy.app.v3;

import com.genug.proxy.app.v1.OrderServiceV1;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 implements OrderServiceV1 {

    private final OrderRepositoryV3 orderRepositoryV3;

    public OrderServiceV3(OrderRepositoryV3 orderRepositoryV3) {
        this.orderRepositoryV3 = orderRepositoryV3;
    }

    public void orderItem(String itemId) {
        this.orderRepositoryV3.save(itemId);
    }
}
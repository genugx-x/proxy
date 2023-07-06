package com.genug.proxy.app.v2;

import com.genug.proxy.app.v1.OrderServiceV1;

public class OrderServiceV2 implements OrderServiceV1 {

    private final OrderRepositoryV2 orderRepositoryV2;

    public OrderServiceV2(OrderRepositoryV2 orderRepositoryV2) {
        this.orderRepositoryV2 = orderRepositoryV2;
    }

    public void orderItem(String itemId) {
        this.orderRepositoryV2.save(itemId);
    }
}

package com.genug.proxy.pureproxy.concreteproxy;

import com.genug.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import com.genug.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import com.genug.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }

    @Test
    void proxyText() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();
    }
}

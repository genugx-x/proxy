package com.genug.proxy.pureproxy.proxy;

import com.genug.proxy.pureproxy.proxy.code.CacheProxy;
import com.genug.proxy.pureproxy.proxy.code.ProxyPatternClient;
import com.genug.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.excute();
        client.excute();
        client.excute();
    }

    @Test
    void cacheProxyText() {
        RealSubject subject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(subject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
        client.excute();
        client.excute();
        client.excute();
    }
}

package com.genug.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        //공통 로직1 시작
        log.info("start1");
        String result1 = target.callA();
        log.info("result1={}", result1);
        //공통 로직1 종료

        //공통 로직2 시작
        log.info("start2");
        String result2 = target.callB();
        log.info("result2={}", result2);
        //공통 로직2 종료
    }

    @Test
    void reflection1() throws Exception {
        Class classHello = Class.forName("com.genug.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();
        // callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
//        Object result1 = methodCallA.invoke(target);
//        log.info("result1={}", result1);
        dynamicCall(methodCallA, target);

        // callA 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    void dynamicCall(Method method, Object target) throws Exception {
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }

}

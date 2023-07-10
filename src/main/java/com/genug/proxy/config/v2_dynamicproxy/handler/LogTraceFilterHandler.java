package com.genug.proxy.config.v2_dynamicproxy.handler;

import com.genug.proxy.trace.TraceStatus;
import com.genug.proxy.trace.logtrace.LogTrace;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class LogTraceFilterHandler implements InvocationHandler {
    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns;

    @Builder
    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;

        String methodName = method.getName();
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target,args);
        }

        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + methodName + "()";
            // log.info("invokeMessage={}", message);
            status = logTrace.begin(message);
            Object result = method.invoke(target, args);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}

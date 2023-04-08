package com.wkk.sprinngdemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kongweikun@163.com
 * @date 2023/4/7
 */
@Component
public class OperateAspect {

    /**
     * 定义切点
     * 横切逻辑
     * 植入
     */
    @Pointcut("@annotation(aop.RecordOperate)")
    public void pointcut() {
    }

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        threadPoolExecutor.execute(() -> {
            try {
                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                RecordOperate annotation = methodSignature.getMethod().getAnnotation(RecordOperate.class);

                Class<? extends Convert> convert = annotation.convert();
                Convert logConvert = convert.newInstance();
                OperateLogDO operateLogDO = logConvert.convert(joinPoint.getArgs()[0]);

                operateLogDO.setDesc(annotation.desc());
                operateLogDO.setResult(result.toString());

                System.out.println("insert operateLog " + operateLogDO.toString());

            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });
        return result;

    }
}

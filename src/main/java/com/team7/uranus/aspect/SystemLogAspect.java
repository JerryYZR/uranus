package com.team7.uranus.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class SystemLogAspect {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void pointcut(JoinPoint point) {

    }

//    @After("pointcut()")
//    public void doAfter(JoinPoint point) throws Throwable {
//        log.info("请求了接口。");
//    }
//
//
//    @After("execution(* com.abc.service.*.many*(..))")
//    public void releaseResource(JoinPoint point) {
//        System.out.println("@After：模拟释放资源...");
//        System.out.println("@After：目标方法为：" +
//                point.getSignature().getDeclaringTypeName() +
//                "." + point.getSignature().getName());
//        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
//        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
//    }
}

package com.team7.uranus.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

@Slf4j
@Component
@Aspect
public class SystemLogAspect {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void pointcut() {

    }

    @After("pointcut()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        log.info("目标方法名为:" + joinPoint.getSignature().getName());
        log.info("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("第" + (i+1) + "个参数为:" + args[i]);
        }
        log.info("被代理的对象:" + joinPoint.getTarget());
        ProceedingJoinPoint pjp = (ProceedingJoinPoint)joinPoint;
        log.info("返回值:"+pjp.proceed());
    }
//
//
//    @After("execution(* com.abc.service.*.many*(..))")
//    public void releaseResource(JoinPoint point) {
//        log.info("@After：模拟释放资源...");
//        log.info("@After：目标方法为：" +
//                point.getSignature().getDeclaringTypeName() +
//                "." + point.getSignature().getName());
//        log.info("@After：参数为：" + Arrays.toString(point.getArgs()));
//        log.info("@After：被织入的目标对象为：" + point.getTarget());
//    }
}

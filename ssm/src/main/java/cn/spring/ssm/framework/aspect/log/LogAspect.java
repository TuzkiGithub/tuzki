package cn.spring.ssm.framework.aspect.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.zz.web.aspect
 * User: 25414
 * Date: 2019/9/3
 * Time: 8:50
 * Description:
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Before(value = "execution(public * cn.spring.ssm.web.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        for (Object arg : args) {
            params.append(arg).append(" ");
        }
        log.info(className + " " + methodName + "()参数：params = {}", params.toString());
    }
}

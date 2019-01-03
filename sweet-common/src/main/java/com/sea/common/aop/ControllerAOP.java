package com.sea.common.aop;


import com.sea.common.beans.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 处理和包装异常
 *
 * @author 肖文杰
 */
@Aspect
@Component
//@Slf4j
public class ControllerAOP {

    @Pointcut("execution(public com.sea.common.vo.ResultBean *(..))")
    public void controllerMethod() {
    }

    @Around("controllerMethod()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        System.out.println("访问时间"+startTime);
        ResultBean<?> result;

        try {
            result = (ResultBean<?>) pjp.proceed();
           // log.info("dldldl");
            System.out.println("xinxx"+pjp.getSignature());
            //log.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {

            result = handlerException(pjp, e);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();

        return result;
    }
}

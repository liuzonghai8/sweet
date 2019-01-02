package com.sea.common.aop;


import com.sea.common.beans.ResultBean;

/**
 * 处理和包装异常
 *
 * @author 肖文杰
 */
//@Aspect
//@Component
public class ControllerAOP {}
//
//    @Pointcut("execution(public cn.xiaowenjie.common.beans.ResultBean *(..))")
//    public void controllerMethod() {
//    }
//
//    @Around("controllerMethod()")
//    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
//        long startTime = System.currentTimeMillis();
//
//        ResultBean<?> result;
//
//        try {
//            result = (ResultBean<?>) pjp.proceed();
//            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
//        } catch (Throwable e) {
//            result = handlerException(pjp, e);
//        }
//
//        return result;
//    }

//    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
//        ResultBean<?> result = new ResultBean();

//        // 已知异常
//        if (e instanceof CheckException) {
//            result.setMsg(e.getLocalizedMessage());
//            result.setCode(ResultBean.FAIL);
//        }
//        // 自己抛出的
//        else if (e instanceof UnloginException ) {
//            result.setMsg("Unlogin");
//            result.setCode(ResultBean.NO_LOGIN);
//        }
//        //shiro异常： 登陆失败，如密码错误
//        else if (e instanceof IncorrectCredentialsException) {
//            result.setMsg("Login failed. Try xwjie/123456");
//            result.setCode(ResultBean.FAIL);
//        }
//        // shiro异常：没有权限
//        else if (e instanceof UnauthorizedException) {
//            result.setMsg("NO PERMISSION: " + e.getMessage());
//            result.setCode(ResultBean.NO_PERMISSION);
//        }
//        //  shiro抛出
//        else if (e instanceof AuthorizationException) {
//            result.setMsg("Unlogin");
//            result.setCode(ResultBean.NO_LOGIN);
//        }
//        else {
//            logger.error(pjp.getSignature() + " error ", e);
//
//            //TODO 未知的异常，应该格外注意，可以发送邮件通知等
//            result.setMsg(e.toString());
//            result.setCode(ResultBean.FAIL);
//        }
//
//        return result;
//    }
//}

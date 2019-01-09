package com.sea.common.exception;

import com.sea.common.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @author bystander
 * @date 2018/9/15
 *
 * 自定义异常类
 */
@Getter
public class SweetException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public SweetException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }


}

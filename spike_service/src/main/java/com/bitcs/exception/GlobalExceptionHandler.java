package com.bitcs.exception;

import com.bitcs.util.CodeMsg;
import com.bitcs.util.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常处理器
 *
 * @author GeChen
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest httpServletRequest, Exception e) {
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return Result.error(globalException.getCodeMsg());
        } else if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            List<ObjectError> objectErrors = bindException.getAllErrors();
            ObjectError objectError = objectErrors.get(0);
            String message = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}

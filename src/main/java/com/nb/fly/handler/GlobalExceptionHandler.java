package com.nb.fly.handler;

import com.nb.fly.response.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: global exception handler
 * @author: Zero
 * @date: 2020/6/26 下午8:54
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    private String defaultExceptionMessage = "System Exception";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO handleException(MethodArgumentNotValidException e) {
        log.error("search data MethodArgumentNotValidException");
        FieldError fieldError = e.getBindingResult().getFieldError();
        return new ResponseVO().error(fieldError != null ? fieldError.getDefaultMessage() : defaultExceptionMessage);
    }

    @ExceptionHandler(BindException.class)
    public ResponseVO handleException(BindException e) {
        log.error("search data BindException");
        FieldError fieldError = e.getBindingResult().getFieldError();
        return new ResponseVO().error(fieldError != null ? fieldError.getDefaultMessage() : defaultExceptionMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseVO handleException(Exception e) {
        log.error("search data Exception", e);
        return new ResponseVO().error(defaultExceptionMessage);
    }
}

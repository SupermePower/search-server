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

    @ExceptionHandler(Throwable.class)
    public ResponseVO handleException(Exception e){
        if (e instanceof MethodArgumentNotValidException) {
            log.error("search data MethodArgumentNotValidException");
            MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException)e;
            FieldError fieldError = argumentNotValidException.getBindingResult().getFieldError();
            return new ResponseVO().error(fieldError.getDefaultMessage());
        }

        if (e instanceof BindException) {
            log.error("search data BindException");
            BindException bindException = (BindException)e;
            FieldError fieldError = bindException.getBindingResult().getFieldError();
            return new ResponseVO().error(fieldError.getDefaultMessage());
        }
        return new ResponseVO().error("System Exception Please Call Admin");
    }
}

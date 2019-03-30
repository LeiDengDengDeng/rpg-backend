package com.design.rpg.exception;

import com.design.rpg.form.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author deng
 * @date 2018/11/13
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Response<String> jsonErrorHandler(ServiceException e) {
        return Response.errResponse(e);
    }
}
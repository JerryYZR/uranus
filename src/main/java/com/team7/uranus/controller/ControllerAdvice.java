package com.team7.uranus.controller;


import com.team7.uranus.Exception.MyException;
import com.team7.uranus.domain.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ResponseBody
@RestControllerAdvice
public class ControllerAdvice {

    // 404错误
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseData<String> NoHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        log.info("","没有的事情");
        return resultFormat(404, ex, "没有这个接口");
    }

    // 运行时出错
    @ExceptionHandler(RuntimeException.class)
    public ResponseData<String> runtimeExceptionHandler(RuntimeException ex) {
        return resultFormat(500, ex, "运行时出错");
    }

    // 缺少参数
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseData<String> requestMissingServletRequest(MissingServletRequestParameterException ex) {
        return resultFormat(400, ex, "缺少参数");
    }

    @ExceptionHandler(value = MyException.class)
    public ResponseData<String> runMyException(MyException ex){
        return resultFormat(ex.getErrorCode(),ex,ex.getErrorMsg());
    }

    private <T extends Throwable> ResponseData<String> resultFormat(Integer code, T ex, String message) {
        ex.printStackTrace();
        log.error("", ex);
        return new ResponseData<>(code, ex.getMessage(), message);
    }
}

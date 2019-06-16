package studio.rollinrock.cnunicom.jphospital.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class DefaultControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public HttpResult<Void> handleIllegalArgumentException(HttpServletRequest request,
                                                           IllegalArgumentException e){
        log.error("uri:{} => illegal argument:", request.getRequestURI(), e.getMessage(), e.getCause());
        return HttpResult.failWithReason("illegal argument:" + e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public HttpResult<Void> handleException(HttpServletRequest request,
                                            Exception e) {
        log.error("uri:{} => exception:", request.getRequestURI(), e.getMessage(), e.getCause());
        return HttpResult.failWithReason("internal server error");
    }
}

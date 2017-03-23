package live.livelab.nginx.api.controller;

import live.livelab.infrastructure.exception.ForbiddenException;
import live.livelab.infrastructure.messaging.ErrorJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zhouxiang on 12/27/2016.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private ErrorAttributes errorAttributes;

    @ExceptionHandler(value = Exception.class)
    public ErrorJson defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        if(!(e instanceof ForbiddenException)) {
            logger.error("An uncaught exception occurred", e);
        }
        return new ErrorJson(response.getStatus(), getErrorAttributes(request, false));
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}

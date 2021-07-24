package org.example.spring.web.advisor;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.web.constants.WebConstants;
import org.example.spring.web.model.response.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author anyuan
 * @since 2021-07-22 15:06
 */
@Slf4j
@ControllerAdvice(basePackages = "org.example.spring.web.controller")
public class CommonExceptionAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Response<?> resolveException(Exception exception) {
        final RequestAttributes currentRequestAttributes = RequestContextHolder.currentRequestAttributes();
        final String requestId = String.valueOf(currentRequestAttributes.getAttribute(WebConstants.REQUEST_ID, RequestAttributes.SCOPE_REQUEST));
        log.error("request id: {}, message: {}", requestId, exception.getMessage(), exception);

        final Response<?> response = Response.ofFailure("SYSTEM_ERROR: " + exception.getMessage());
        response.setRequestId(requestId);
        return response;
    }
}

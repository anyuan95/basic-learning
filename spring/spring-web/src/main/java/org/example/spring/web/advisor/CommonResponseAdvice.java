package org.example.spring.web.advisor;

import org.example.spring.web.constants.WebConstants;
import org.example.spring.web.model.response.Response;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author anyuan
 * @since 2021-07-22 14:51
 */
@Order(0)
@ControllerAdvice(basePackages = {"org.example.spring.web.controller"})
public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        final Class<?> parameterType = returnType.getParameterType();
        return !(parameterType == Response.class || HttpEntity.class.isAssignableFrom(parameterType));
    }

    @Override
    public Response<Object> beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        final Response<Object> packedResponse = Response.ofSuccess(body);
        final RequestAttributes currentRequestAttributes = RequestContextHolder.currentRequestAttributes();
        final String requestId = String.valueOf(currentRequestAttributes.getAttribute(WebConstants.REQUEST_ID, RequestAttributes.SCOPE_REQUEST));
        packedResponse.setRequestId(requestId);
        return packedResponse;
    }
}

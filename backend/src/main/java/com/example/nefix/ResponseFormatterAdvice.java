package com.example.nefix;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseFormatterAdvice implements ResponseBodyAdvice<Object>
{
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType)
    {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response)
    {
        String acceptHeader = request.getHeaders().getFirst("Accept");

        if (acceptHeader != null)
        {
            if (acceptHeader.equals(MediaType.APPLICATION_XML_VALUE))
            {
                response.getHeaders().setContentType(MediaType.APPLICATION_XML);
            } else if (acceptHeader.equals(MediaType.TEXT_PLAIN_VALUE))
            {
                response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
                return body.toString();
            }
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return body;
    }
}

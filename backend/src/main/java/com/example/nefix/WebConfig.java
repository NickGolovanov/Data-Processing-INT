package com.example.nefix;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        converters.add(new MappingJackson2XmlHttpMessageConverter());
    }
}

package com.example.nefix;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer
{

    //    @Bean
    //    public CorsFilter corsFilter()
    //    {
    //        CorsConfiguration config = new CorsConfiguration();
    //        config.setAllowCredentials(true);

    /// /        config.addAllowedOrigin("http://localhost:3000");
    //        config.addAllowedHeader("*");
    //        config.addAllowedMethod("*");
    //
    //        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //        source.registerCorsConfiguration("/**", config); // Apply CORS to all endpoints
    //
    //        return new CorsFilter(source);
    //    }
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        converters.add(new MappingJackson2XmlHttpMessageConverter());
    }
}

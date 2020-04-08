package com.example.test.config;

import com.example.test.config.converter.DateToStringConverter;
import com.example.test.config.converter.StringToDateConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan(basePackages = "com.example.test.config")
@EnableWebMvc   //代替<mvc:annotation-driven>
public class WebConfig implements WebMvcConfigurer {

    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        registry.addConverter(new DateToStringConverter());
    }
}

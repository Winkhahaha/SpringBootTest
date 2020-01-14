package com.example.springbootweb1.config;

import com.example.springbootweb1.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//WebMvcConfigurerAdapter可以扩展mvc功能
//@EnableWebMvc: SpringBoot对mvc配置失效,我们接管mvc
//@EnableWebMvc
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送/xaled请求也来到success页面
        registry.addViewController("/xaled").setViewName("success");
    }

    //WebMvcConfigurerAdapter组件都会一起起作用
    @Bean//将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源: *css,*.js
                //SpringBoot已经做好了静态资源映射
                //registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                       // .excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return adapter;
    }

    //区域信息解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
}

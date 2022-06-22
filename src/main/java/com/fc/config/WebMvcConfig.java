package com.fc.config;

import com.fc.common.JacksonObjectMapper;
import com.fc.filter.LoginCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.List;

@Configuration
//@ServletComponentScan("com.fc.filter")
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private JacksonObjectMapper jacksonObjectMapper;
    @Autowired
    private LoginCheckFilter loginCheckFilter;

    // 配置自定义过滤器
    @Bean
    FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(loginCheckFilter);
        // 设置优先级
        bean.setOrder(1);
        // 字符串转为集合
        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;
    }

    /**
     * 扩展mvc框架的消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建消息转换器对象并设置对象转换器
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(jacksonObjectMapper);

        // 将上面的消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0, messageConverter);
    }
}

package com.guonanjun.demo.config;

import com.guonanjun.util.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述： Spring 配置类
 *
 * @author guonanjun
 * @date 18-6-23
 */
@Configuration
public class SpringConfig {

    @Bean
    public SpringContextUtil getSpringContextUtil() {
        return new SpringContextUtil();
    }
}

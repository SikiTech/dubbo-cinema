/**
 * projectName: dubbo-cinema
 * fileName: WebConfig.java
 * packageName: com.sikiapp.gateway.config
 * date: 2020-03-06 下午12:24
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.gateway.config;

import com.sikiapp.gateway.auth.AuthFilter;
import com.sikiapp.gateway.properties.RestProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: WebConfig
 * @packageName: com.sikiapp.gateway.config
 * @description: web 配置
 * @author: Robert
 * @data: 2020-03-06 下午12:24
 * @version: V1.0
 **/
@Configuration
public class WebConfig {

    @Bean
    @ConditionalOnProperty(prefix = RestProperties.REST_PREFIX, name = "auth-open", havingValue = "true", matchIfMissing = true)
    public AuthFilter jwtAuthenticationTokenFilter() {
        return new AuthFilter();
    }

}
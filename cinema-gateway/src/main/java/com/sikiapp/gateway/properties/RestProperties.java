/**
 * projectName: dubbo-cinema
 * fileName: RestProperties.java
 * packageName: com.sikiapp.gateway.properties
 * date: 2020-03-06 下午12:11
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.gateway.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @className: RestProperties
 * @packageName: com.sikiapp.gateway.properties
 * @description: 项目相关配置
 * @author: Robert
 * @data: 2020-03-06 下午12:11
 * @version: V1.0
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@Configuration
@ConfigurationProperties(RestProperties.REST_PREFIX)
public class RestProperties {

    public static final String REST_PREFIX = "rest";

    private boolean authOpen = true;

    private boolean signOpen = true;

}
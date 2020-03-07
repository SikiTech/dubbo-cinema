/**
 * projectName: dubbo-cinema
 * fileName: JwtProperties.java
 * packageName: com.sikiapp.gateway.config
 * date: 2020-03-06 上午10:57
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.gateway.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @className: JwtProperties
 * @packageName: com.sikiapp.gateway.config
 * @description:
 * @author: Robert
 * @data: 2020-03-06 上午10:57
 * @version: V1.0
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";

    private String header = "Authorization";

    private String secret = "defaultSecret";

    private Long expiration = 604800L;

    private String authPath = "auth";

    private String md5Key = "randomKey";

    private String ignoreUrl = "";

}
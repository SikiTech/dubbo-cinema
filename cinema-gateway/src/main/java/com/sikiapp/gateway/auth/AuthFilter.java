/**
 * projectName: dubbo-cinema
 * fileName: AuthFilter.java
 * packageName: com.sikiapp.gateway.auth
 * date: 2020-03-06 上午11:32
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.gateway.auth;

import com.sikiapp.gateway.common.CurrentUser;
import com.sikiapp.gateway.properties.JwtProperties;
import com.sikiapp.gateway.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @className: AuthFilter
 * @packageName: com.sikiapp.gateway.auth
 * @description: 对客户端请求的jwt token验证过滤器
 * @author: Robert
 * @data: 2020-03-06 上午11:32
 * @version: V1.0
 **/
public class AuthFilter extends OncePerRequestFilter {

    public static final Pattern COMMA_SPLIT_PATTERN = Pattern
            .compile("\\s*[,]+\\s*");

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (request.getServletPath().equals("/" + jwtProperties.getAuthPath())) {
            chain.doFilter(request, response);
            return;
        }

        // 配置忽略列表
        String ignoreUrl = jwtProperties.getIgnoreUrl();
        String[] ignoreUrls = COMMA_SPLIT_PATTERN.split(ignoreUrl);
        for(int i = 0; i < ignoreUrls.length; i++){
            if(request.getServletPath().equals(ignoreUrls[i])){
                chain.doFilter(request, response);
                return;
            }
        }

        final String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            // 通过Token获取userID，并且将之存入Threadlocal，以便后续业务调用
            String userId = jwtTokenUtil.getUsernameFromToken(authToken);
            if(userId == null){
                return;
            } else {
                CurrentUser.saveUserId(userId);
            }

            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
//                    RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_EXPIRED.getCode(), BizExceptionEnum.TOKEN_EXPIRED.getMessage()));
                    return;
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
//                RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
                return;
            }
        } else {
            //header没有带Bearer字段
//            RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
            return;
        }
        chain.doFilter(request, response);
    }
}
/**
 * projectName: dubbo-cinema
 * fileName: AuthController.java
 * packageName: com.sikiapp.gateway.controller
 * date: 2020-03-06 上午11:58
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.gateway.controller;

import com.sikiapp.cinema.user.api.UserService;
import com.sikiapp.cinema.user.api.vo.UserModel;
import com.sikiapp.gateway.controller.dto.AuthRequest;
import com.sikiapp.gateway.controller.dto.AuthResponse;
import com.sikiapp.gateway.util.JwtTokenUtil;
import com.sikiapp.gateway.vo.ResponseVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className: AuthController
 * @packageName: com.sikiapp.gateway.controller
 * @description: 鉴权控制
 * @author: Robert
 * @data: 2020-03-06 上午11:58
 * @version: V1.0
 **/
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Reference(interfaceClass = UserService.class,check = false)
    private UserService userService;

    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseVO createAuthenticationToken(@RequestBody AuthRequest authRequest) {

        boolean validate = true;
        // 验证用户名和密码
        int userId = userService.login(authRequest.getUserName(),authRequest.getPassword());
        if(userId == 0){
            validate = false;
        }

        if (validate) {
            // randomKey和token已经生成完毕
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken("" + userId, randomKey);
            // 返回值
            return ResponseVO.success(new AuthResponse(token, randomKey));
        } else {
            return ResponseVO.serviceFail("用户名或密码错误");
        }
    }

    @PostMapping("/register")
    public ResponseVO register(@RequestBody UserModel userModel) {
        boolean result = userService.register(userModel);
        return ResponseVO.success(userModel);
    }




}
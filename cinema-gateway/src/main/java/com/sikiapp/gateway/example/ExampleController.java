package com.sikiapp.gateway.example;

import com.sikiapp.gateway.common.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Controller
@RequestMapping("/hello")
public class ExampleController {

    @RequestMapping("")
    public ResponseEntity hello() {

        System.out.println(CurrentUser.getCurrentUser());

        return ResponseEntity.ok("请求成功!");
    }
}

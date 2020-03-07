/**
 * projectName: redis
 * fileName: BaseController.java
 * packageName: com.sikiapp.redis.controller
 * date: 2019-06-03 下午4:39
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: BaseController
 * @packageName: com.sikiapp.redis.controller
 * @description:
 * @author: Robert
 * @data: 2019-06-03 下午4:39
 * @version: V1.0
 **/
@Slf4j
public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    protected String redirectTo( String url ) {
        StringBuffer rto = new StringBuffer("redirect:");
        rto.append(url);
        return rto.toString();
    }

}
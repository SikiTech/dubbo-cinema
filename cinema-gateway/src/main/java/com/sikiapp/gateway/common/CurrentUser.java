/**
 * projectName: dubbo-cinema
 * fileName: CurrentUser.java
 * packageName: com.sikiapp.gateway.common
 * date: 2020-03-06 下午11:54
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.gateway.common;

/**
 * @className: CurrentUser
 * @packageName: com.sikiapp.gateway.common
 * @description:
 * @author: Robert
 * @data: 2020-03-06 下午11:54
 * @version: V1.0
 **/
public class CurrentUser {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void saveUserId(String userId){

        threadLocal.set(userId);
    }
    public static String getCurrentUser(){
        return threadLocal.get();
    }
}
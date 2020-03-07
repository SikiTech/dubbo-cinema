package com.sikiapp.cinema.user.api;

import com.sikiapp.cinema.user.api.vo.UserInfoModel;
import com.sikiapp.cinema.user.api.vo.UserModel;

public interface UserService {

    /**
     * 注册
     * @param userModel 前端DTO
     * @return
     */
    boolean register(UserModel userModel);

    /**
     * 登录验证
     * @param username 用户名
     * @param password 密码
     * @return
     */
    int login(String username, String password);

    /**
     * 检验用户是否存在
     * @param username 用户名
     * @return
     */
    boolean checkUsername(String username);

    /**
     * 查询用户信息
     * @param uuid 用户id
     * @return
     */
    UserInfoModel getUserInfo(int uuid);

    /**
     * 更新用户信息
     * @param userInfoModel 前端DTO
     * @return
     */
    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);

}

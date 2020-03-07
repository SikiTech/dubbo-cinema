/**
 * projectName: dubbo-cinema
 * fileName: UserServiceImpl.java
 * packageName: com.sikiapp.user.service.impl
 * date: 2020-03-05 下午10:33
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sikiapp.cinema.core.util.MD5Util;
import com.sikiapp.cinema.user.api.UserService;
import com.sikiapp.cinema.user.api.vo.UserInfoModel;
import com.sikiapp.cinema.user.api.vo.UserModel;
import com.sikiapp.user.entity.MoocUserT;
import com.sikiapp.user.mapper.MoocUserTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: UserServiceImpl
 * @packageName: com.sikiapp.user.service.impl
 * @description:
 * @author: Robert
 * @data: 2020-03-05 下午10:33
 * @version: V1.0
 **/
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private MoocUserTMapper moocUserTMapper;

    @Override
    public boolean register(UserModel userModel) {
        // 将注册信息实体转换为数据实体[mooc_user_t]
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(userModel.getUsername());
        moocUserT.setEmail(userModel.getEmail());
        moocUserT.setAddress(userModel.getAddress());
        moocUserT.setUserPhone(userModel.getPhone());
        // 创建时间和修改时间 -> current_timestamp

        // 数据加密 【MD5混淆加密 + 盐值 -> Shiro加密】
        String md5Password = MD5Util.encrypt(userModel.getPassword());
        moocUserT.setUserPwd(md5Password);

        // 将数据实体存入数据库
        Integer insert = moocUserTMapper.insert(moocUserT);
        if(insert>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int login(String username, String password) {
        // 根据登陆账号获取数据库信息
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(username);

        MoocUserT result = moocUserTMapper.selectOne(Wrappers.<MoocUserT>lambdaQuery()
                .eq(MoocUserT::getUserName, username));

        // 获取到的结果，然后与加密以后的密码做匹配
        if(result != null && result.getUuid() > 0){
            String md5Password = MD5Util.encrypt(password);
            if(result.getUserPwd().equals(md5Password)){
                return result.getUuid();
            }
        }
        return 0;
    }

    @Override
    public boolean checkUsername(String username) {
        return false;
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        return null;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        return null;
    }
}
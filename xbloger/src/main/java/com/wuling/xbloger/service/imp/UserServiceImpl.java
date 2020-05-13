package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.User;
import com.wuling.xbloger.mapper.UserMapper;
import com.wuling.xbloger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc: 用户业务处理
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String username, String password) {
        User user = userMapper.getUserByUserName(username);
        if (null == user) {
            // return invalid token
            return "";
        }
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5Pass.equals(user.getPassword())) {
            // return invalid token
            return "";
        }
        return null;
    }

    @Override
    public Boolean logout() {
        return null;
    }
}

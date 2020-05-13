package com.wuling.xbloger.service;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc: TODO
 */
public interface UserService {

    /**
     * 管理员登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 会话token，有时效性
     */
    String login(String username, String password);

    /**
     * 注销登录
     *
     * @return
     */
    Boolean logout();

}

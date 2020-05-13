package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc:
 */
public interface UserMapper {

    @Select("select id, username, password from user where username = #{username}")
    User getUserByUserName(String username);

}

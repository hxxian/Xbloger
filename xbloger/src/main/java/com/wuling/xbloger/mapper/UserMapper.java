package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc:
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select user_id, username, password from user where username = #{username}")
    @Results({
            @Result(property = "userId", column = "user_id")
    })
    User getUserByUserName(String username);

}

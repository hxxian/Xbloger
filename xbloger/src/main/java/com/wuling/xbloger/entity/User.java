package com.wuling.xbloger.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc:
 */
@Setter
@Getter
public class User extends Entity {

    private Long userId;
    private String username;
    private String password;

}

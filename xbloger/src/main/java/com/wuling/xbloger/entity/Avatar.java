package com.wuling.xbloger.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: xian
 * @Description: 头像
 * @Date:create in 2020/5/12 8:07
 */
@Setter
@Getter
public class Avatar extends Entity {

    private Integer aid;
    private String avatarUrl;

}

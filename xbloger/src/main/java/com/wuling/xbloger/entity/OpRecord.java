package com.wuling.xbloger.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: wu_ling
 * @Date: 2020/6/7
 * @Desc: 管理员操作记录
 */
@Setter
@Getter
public class OpRecord {

    private Long id;
    // 操作描述
    private String opDesc;
    // 操作人
    private String opUser;
    // 操作人ip
    private String ipAddr;

    private Date gmtCreate;

}

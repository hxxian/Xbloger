package com.wuling.xbloger.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/6/7
 * @Desc:
 */
@Setter
@Getter
public class OpRecordVO {

    private Long id;
    // 操作描述
    private String opDesc;
    // 操作人
    private String opUser;
    // 操作人ip
    private String ipAddr;

    private Long gmtCreate;

}

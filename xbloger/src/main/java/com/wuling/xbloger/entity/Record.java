package com.wuling.xbloger.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: wu_ling
 * @Date: 2020/6/4
 * @Desc: 操作记录实体
 */
@Setter
@Getter
public class Record {

    private Long id;
    private String recordTableName;
    private String recordTableColumn;
    private Long recordId;
    private String oldValue;
    private String newValue;
    private String opUser;
    private String ipAddr;
    private String desc;

    private Date gmtCreate;
    private Date gmtUpdate;

}

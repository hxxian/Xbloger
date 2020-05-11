package com.wuling.xbloger.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: huang xiao xian
 * @Date: 2019/8/9
 * @Des:
 */
@Setter
@Getter
public class NotifyLog {

    private Long logId;
    private Integer total;
    private Integer aSuccess;
    private Integer iSuccess;
    private Integer aFail;
    private Integer iFail;
    private Integer aNotNeed;
    private Integer iNotNeed;
    private String extend;
    private Date gmtCreate;
    private Date gmtUpdate;
    private Date startTime;

}

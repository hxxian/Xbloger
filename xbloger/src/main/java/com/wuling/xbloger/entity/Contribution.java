package com.wuling.xbloger.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: wu_ling
 * @Date: 2020/6/20
 * @Desc:
 */
@Setter
@Getter
public class Contribution {

    private Long id;
    private Integer type;
    private String desc;
    private Date gmtCreate;
    private Date gmtUpdate;

}

package com.wuling.xbloger.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xian
 * @Description: 语录摘（说说）
 * @Date:create in 2020/5/12 8:06
 */
@Setter
@Getter
public class Diary extends Entity {

    private Long did;
    private String content;

    private Date gmtCreate;
    private Date gmtUpdate;

}

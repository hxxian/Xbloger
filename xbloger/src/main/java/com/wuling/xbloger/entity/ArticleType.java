package com.wuling.xbloger.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xian
 * @Description: 文章类别
 * @Date:create in 2020/5/12 8:08
 */
@Setter
@Getter
public class ArticleType extends Entity {

    private Long typeId;
    private String typeName;

    private Date gmtCreate;
    private Date gmtUpdate;
}

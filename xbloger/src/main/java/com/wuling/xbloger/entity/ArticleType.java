package com.wuling.xbloger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // 非表字段
    @JsonIgnore
    private Integer articleCount;

    private Date gmtCreate;
    private Date gmtUpdate;
}

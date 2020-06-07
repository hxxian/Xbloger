package com.wuling.xbloger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xian
 * @Description: 文章详情
 * @Date:create in 2020/5/12 8:10
 */
@Setter
@Getter
public class Article extends Entity {

    private Long articleId;
    private Long typeId;
    private String title;
    private String content;
    private String digest;

    private Date gmtCreate;
    private Date gmtUpdate;

    // 联表查询扩展字段

    @JsonIgnore
    private String typeName;
}

package com.wuling.xbloger.entity;

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
public class Article {

    private Long articleId;
    private Integer typeId;
    private String title;
    private String content;

    private Date gmtCreate;
    private Date gmtUpdate;
}

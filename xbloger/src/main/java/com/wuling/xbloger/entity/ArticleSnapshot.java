package com.wuling.xbloger.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xian
 * @Description: 文章信息快照
 * @Date:create in 2020/5/12 8:12
 */
@Setter
@Getter
public class ArticleSnapshot {

    private Long sid;
    private Long articleId;

    // 文章标题
    private String title;
    // 文章摘要
    private String digest;
    // 文章发布时间
    private Date publishTime;
    // 文章显示状态
    private Integer showState;
    // 阅读数
    private Integer readCount;
    // 评论数
    private Integer commentCount;

    private Date gmtCreate;
    private Date gmtUpdate;
}

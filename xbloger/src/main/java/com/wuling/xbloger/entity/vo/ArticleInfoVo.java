package com.wuling.xbloger.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/5/17
 * @Desc:文章详情页VO
 */
@Getter
@Setter
public class ArticleInfoVo {

    private Long articleId;
    private String title;
    private String content;
    private String typeName;
    private Long publishTimestamp;
    private Integer readCount;

    // 上一篇文章ID
    private Long lastArticleId;
    // 上一篇文章标题
    private String lastArticleTitle;
    // 下一篇文章ID
    private Long nextArticleId;
    // 下一篇文章标题
    private String nextArticleTitle;

}

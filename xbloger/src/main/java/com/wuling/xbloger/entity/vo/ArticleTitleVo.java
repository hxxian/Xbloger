package com.wuling.xbloger.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/5/17
 * @Desc: 文章列表页文章标题VO
 */
@Getter
@Setter
public class ArticleTitleVo {

    private Long articleId;
    private Long publishTime;
    private String articleTitle;

}

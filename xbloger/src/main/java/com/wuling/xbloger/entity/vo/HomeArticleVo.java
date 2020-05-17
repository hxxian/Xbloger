package com.wuling.xbloger.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/5/16
 * @Desc: 首页文章快照VO
 */
@Setter
@Getter
public class HomeArticleVo {

    private Long articleId;
    private String title;
    private String digest;
    private Long publishTimestamp;
    private Integer readCount;

}

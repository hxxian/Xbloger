package com.wuling.xbloger.entity.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/5/19
 * @Desc: TODO
 */
@Setter
@Getter
public class HomeArticleBO {

    // 1 - 最新，2 - 最热
    private Integer tag;

    private Long articleId;
    private String title;
    private String digest;
    private Long publishTimestamp;
    private Integer readCount;

}

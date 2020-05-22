package com.wuling.xbloger.entity.bo;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: wu_ling
 * @Date: 2020/5/18
 * @Desc:
 */
@Getter
@Setter
public class ArticleInfoBo {

    private Long articleId;
    private Integer typeId;
    private String typeName;
    private String title;
    private String content;
    private Date publishTime;
    private Long publishTimestamp;
    private Integer readCount;

}

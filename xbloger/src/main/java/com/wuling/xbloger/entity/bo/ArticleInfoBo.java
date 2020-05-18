package com.wuling.xbloger.entity.bo;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/5/18
 * @Desc:
 */
@Getter
@Setter
public class ArticleInfoBo {

    private Article article;
    private Integer readCount;

}

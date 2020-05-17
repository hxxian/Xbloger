package com.wuling.xbloger.controller.client.response;

import com.wuling.xbloger.entity.vo.ArticleTitleVo;
import com.wuling.xbloger.entity.vo.ArticleTypeVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/17
 * @Desc:
 */
@Setter
@Getter
public class ArticleListResponse {

    private List<ArticleTypeVo> articleTypes;
    private List<ArticleTitleVo> articleTitles;

}

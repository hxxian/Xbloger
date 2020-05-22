package com.wuling.xbloger.controller.client.response;

import com.wuling.xbloger.entity.vo.ArticleTitleVO;
import com.wuling.xbloger.entity.vo.ArticleTypeVO;
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

    private List<ArticleTypeVO> articleTypes;
    private List<ArticleTitleVO> articleTitles;

}

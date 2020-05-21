package com.wuling.xbloger.entity.vo;

import com.wuling.xbloger.entity.bo.HomeArticleBO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/16
 * @Desc: 首页数据VO
 */
@Setter
@Getter
public class HomeVo {

    private List<HomeArticleBO> articles;
    private List<ArticleTitleVo> articleTitles;
    private String diaryContent;

}

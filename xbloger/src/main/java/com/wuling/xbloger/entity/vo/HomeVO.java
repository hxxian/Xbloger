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
public class HomeVO {

    private List<HomeArticleBO> articles;
    private List<ArticleTitleVO> articleTitles;
    private String diaryContent;
    private Long diaryTimestamp;

}

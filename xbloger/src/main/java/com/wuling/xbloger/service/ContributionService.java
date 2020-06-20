package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.bo.ContributionBO;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/6/20
 * @Desc:
 */
public interface ContributionService {

    /**
     * 查询当前年份的博客贡献活动
     *
     * @return
     */
    ContributionBO listContributionInCurrYear();

}

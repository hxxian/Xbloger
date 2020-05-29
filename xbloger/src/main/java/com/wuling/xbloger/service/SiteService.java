package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.SiteSnapshot;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc:
 */
public interface SiteService {

    /**
     * 获取网站快照信息
     *
     * @param siteId
     * @return
     */
    SiteSnapshot getSiteSnapshot(Integer siteId);

}

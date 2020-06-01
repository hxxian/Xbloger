package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.SiteSnapshot;
import com.wuling.xbloger.mapper.SiteSnapshotMapper;
import com.wuling.xbloger.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc:
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteSnapshotMapper siteSnapshotMapper;

    @Override
    public SiteSnapshot getSiteSnapshot(Integer siteId) {
        return siteSnapshotMapper.getSiteSnapshot(siteId);
    }

}

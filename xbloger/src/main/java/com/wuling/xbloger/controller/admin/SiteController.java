package com.wuling.xbloger.controller.admin;

import com.wuling.xbloger.constant.KeyIdConstant;
import com.wuling.xbloger.entity.SiteSnapshot;
import com.wuling.xbloger.entity.vo.SiteSnapshotVO;
import com.wuling.xbloger.service.SiteService;
import com.wuling.xbloger.util.ObjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc:
 */
@RestController
@RequestMapping("site/snapshot")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @GetMapping
    public ResponseEntity<SiteSnapshotVO> getSiteSnapshot() {
        SiteSnapshot siteSnapshot = siteService.getSiteSnapshot(KeyIdConstant.SITE_SNAPSHOT_ID);
        SiteSnapshotVO vo = ObjectBuilder.buildSiteSnapshotVO(siteSnapshot);
        return ResponseEntity.ok(vo);
    }

}

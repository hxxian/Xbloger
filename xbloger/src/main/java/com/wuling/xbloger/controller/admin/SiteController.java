package com.wuling.xbloger.controller.admin;

import com.wuling.xbloger.constant.KeyIdConstant;
import com.wuling.xbloger.entity.OpRecord;
import com.wuling.xbloger.entity.SiteSnapshot;
import com.wuling.xbloger.entity.bo.LatestCommentBO;
import com.wuling.xbloger.entity.vo.OpRecordVO;
import com.wuling.xbloger.entity.vo.SiteSnapshotVO;
import com.wuling.xbloger.service.CommentService;
import com.wuling.xbloger.service.OpRecordService;
import com.wuling.xbloger.service.SiteService;
import com.wuling.xbloger.util.ObjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc:
 */
@RestController
@RequestMapping("admin/site/snapshot")
public class SiteController {

    @Autowired
    private SiteService siteService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private OpRecordService opRecordService;

    @GetMapping
    public ResponseEntity<SiteSnapshotVO> getSiteSnapshot() {
        SiteSnapshot siteSnapshot = siteService.getSiteSnapshot(KeyIdConstant.SITE_SNAPSHOT_ID);
        SiteSnapshotVO vo = ObjectBuilder.buildSiteSnapshotVO(siteSnapshot);

        List<LatestCommentBO> commentBOS = commentService.listLatest10Comment();
        vo.setLatestComments(commentBOS);
        return ResponseEntity.ok(vo);
    }

    @GetMapping("oprecord/{page}")
    public ResponseEntity<List<OpRecordVO>> listOpRecord(@PathVariable("page") Integer page) {
        List<OpRecord> list = opRecordService.listByPage(page);
        if (list != null && !list.isEmpty()) {
            List<OpRecordVO> vos = new ArrayList<>(list.size());
            list.forEach(r -> {vos.add(ObjectBuilder.buildOpRecordVo(r));});
            return ResponseEntity.ok(vos);
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

}

package com.wuling.xbloger.controller.admin;

import com.wuling.xbloger.annotation.OperateRecord;
import com.wuling.xbloger.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wu_ling
 * @Date: 2020/6/4
 * @Desc: 说说、日记访问控制
 */
@RestController
@RequestMapping("admin/diary")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @PostMapping
    @OperateRecord("发表说说")
    public ResponseEntity<Void> saveDiary(String content) {
        if (StringUtils.isEmpty(content)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        diaryService.saveDiary(content);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}

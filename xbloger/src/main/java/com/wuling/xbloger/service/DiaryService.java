package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.Diary;

/**
 * @Author: wu_ling
 * @Date: 2020/5/21
 * @Desc:
 */
public interface DiaryService {

    /**
     * 新增说说
     *
     * @param content
     */
    void saveDiary(String content);

    /**
     * 获取最新的说说
     *
     * @return
     */
    Diary getLatestDiary();

}

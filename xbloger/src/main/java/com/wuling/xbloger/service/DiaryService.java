package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.Diary;

/**
 * @Author: wu_ling
 * @Date: 2020/5/21
 * @Desc:
 */
public interface DiaryService {

    /**
     * 获取最新的说说
     *
     * @return
     */
    Diary getLatestDiary();

}

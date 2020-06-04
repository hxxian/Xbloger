package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.Diary;
import com.wuling.xbloger.mapper.DiaryMapper;
import com.wuling.xbloger.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/21
 * @Desc:
 */
@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    public void saveDiary(String content) {
        Diary diary = new Diary();
        diary.setContent(content);
        diary.setGmtCreate(new Date());
        diary.setGmtUpdate(new Date());

        diaryMapper.insertDiary(diary);
    }

    @Override
    public Diary getLatestDiary() {
        List<Diary> diaries = diaryMapper.listDiary(1);
        if (diaries != null && !diaries.isEmpty()) {
            return diaries.get(0);
        }
        return new Diary();
    }
}

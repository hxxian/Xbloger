package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.Diary;
import com.wuling.xbloger.enumeration.ContributionEnum;
import com.wuling.xbloger.mapper.ContributionMapper;
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
    @Autowired
    private ContributionMapper contributionMapper;

    @Override
    public void saveDiary(String content) {
        Diary diary = new Diary();
        diary.setContent(content);
        diary.setGmtCreate(new Date());
        diary.setGmtUpdate(new Date());

        diaryMapper.insert(diary);

        contributionMapper.insert(ContributionEnum.CREATE_DIARY.getTypeId(), ContributionEnum.CREATE_DIARY.getDesc());
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

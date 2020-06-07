package com.wuling.xbloger;

import com.wuling.xbloger.entity.Diary;
import com.wuling.xbloger.entity.bo.ArticleInfoBO;
import com.wuling.xbloger.mapper.ArticleMapper;
import com.wuling.xbloger.mapper.ArticleSnapshotMapper;
import com.wuling.xbloger.mapper.DiaryMapper;
import com.wuling.xbloger.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XblogerApplicationTests {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleSnapshotMapper articleSnapshotMapper;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private DiaryMapper diaryMapper;


    @Test
    void contextLoads() {
        System.out.println();
    }

    @Test
    void testInsertArticle() {
        long typeId = 1;
        String title = "test title";
        String content = "test content";
        String digest = "test digest";
        articleService.addArticle(typeId, title, content, digest);
    }

}

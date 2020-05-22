package com.wuling.xbloger;

import com.wuling.xbloger.entity.bo.ArticleInfoBO;
import com.wuling.xbloger.mapper.ArticleMapper;
import com.wuling.xbloger.mapper.ArticleSnapshotMapper;
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


    @Test
    void contextLoads() {
        ArticleInfoBO bo = articleSnapshotMapper.getArticleInfoBoByArticleId(3L);
        System.out.println();
    }

    @Test
    void testInsertArticle() {
        int typeId = 1;
        String title = "test title";
        String content = "test content";
        String digest = "test digest";
        articleService.addArticle(typeId, title, content, digest);
    }

    @Test
    void testArticleController() {
    }

}

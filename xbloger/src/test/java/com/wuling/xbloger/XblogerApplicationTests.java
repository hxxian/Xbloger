package com.wuling.xbloger;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.mapper.ArticleMapper;
import com.wuling.xbloger.mapper.ArticleSnapshotMapper;
import com.wuling.xbloger.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

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
        Article article = new Article();
        article.setTypeId(1);
        article.setTitle("test");
        article.setContent("ajsdlfjaklsjflajsflas");
        article.setGmtCreate(new Date());
        article.setGmtUpdate(new Date());
        articleMapper.insertArticle(article);
    }

    @Test
    void testInsertArticle() {
        int typeId = 1;
        String title = "test title";
        String content = "test content";
        String digest = "test digest";
        articleService.addArticle(typeId, title, content, digest);
    }

}

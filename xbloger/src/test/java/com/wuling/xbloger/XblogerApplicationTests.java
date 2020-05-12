package com.wuling.xbloger;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.mapper.ArticleMapper;
import com.wuling.xbloger.mapper.ArticleSnapshotMapper;
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

}

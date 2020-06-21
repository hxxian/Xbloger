package com.wuling.xbloger;

import com.wuling.xbloger.entity.Contribution;
import com.wuling.xbloger.entity.Diary;
import com.wuling.xbloger.entity.bo.ArticleInfoBO;
import com.wuling.xbloger.enumeration.ContributionEnum;
import com.wuling.xbloger.mapper.ArticleMapper;
import com.wuling.xbloger.mapper.ArticleSnapshotMapper;
import com.wuling.xbloger.mapper.ContributionMapper;
import com.wuling.xbloger.mapper.DiaryMapper;
import com.wuling.xbloger.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

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
    @Autowired
    private ContributionMapper contributionMapper;


    @Test
    void contextLoads() {
        System.out.println();
    }

    @Test
    void testInsertArticle() {
        String password = "";
        String res = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(res);
    }

    @Test
    void testInsertContribution() {
        Contribution contribution = new Contribution();
        contribution.setType(ContributionEnum.CREATE_ARTICLE.getTypeId());
        contribution.setDes(ContributionEnum.CREATE_ARTICLE.getDesc());
    }

}

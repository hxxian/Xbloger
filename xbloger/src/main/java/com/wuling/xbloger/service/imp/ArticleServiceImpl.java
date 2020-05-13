package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.mapper.ArticleMapper;
import com.wuling.xbloger.mapper.ArticleSnapshotMapper;
import com.wuling.xbloger.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc:
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleSnapshotMapper articleSnapshotMapper;

    @Override
    public Boolean addArticle(Integer typeId, String title, String content, String digest) {
        try {
            Article article = genArticle(typeId, title, content, digest);
            // 执行 插入成功后，mybatis会返回当前记录的主键ID，并赋值给当前对象
            articleMapper.insertArticle(article);

            ArticleSnapshot snapshot = genArticleSnapshot(article);
            articleSnapshotMapper.addArticleSnap(snapshot);
            return true;
        } catch (Exception e) {
            log.error("添加文章异常， e： ", e);
        }

        return false;
    }

    @Override
    public Article getArticle(Long articleId) {
        Article article = articleMapper.getById(articleId);
        return article;
    }

    private Article genArticle(int typeId, String title, String content, String digest) {
        Article article = new Article();
        article.setTypeId(typeId);
        article.setTitle(title);
        article.setContent(content);
        article.setDigest(digest);
        article.setGmtCreate(new Date());
        article.setGmtUpdate(new Date());
        return article;
    }

    private ArticleSnapshot genArticleSnapshot(Article article) {
        ArticleSnapshot snapshot = new ArticleSnapshot();
        snapshot.setArticleId(article.getArticleId());
        snapshot.setDigest(article.getDigest());
        snapshot.setTitle(article.getTitle());
        snapshot.setPublishTime(article.getGmtCreate());
        snapshot.setGmtCreate(new Date());
        snapshot.setGmtUpdate(new Date());
        return snapshot;
    }
}

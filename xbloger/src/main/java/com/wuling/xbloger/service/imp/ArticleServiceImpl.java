package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.constant.KeyIdConstant;
import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.mapper.ArticleMapper;
import com.wuling.xbloger.mapper.ArticleSnapshotMapper;
import com.wuling.xbloger.mapper.SiteSnapshotMapper;
import com.wuling.xbloger.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc:
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final Integer ARTICLE_PAGE_SIZE = 10;
    private final Integer ADMIN_ARTICLE_PAGE_SIZE = 20;

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleSnapshotMapper articleSnapshotMapper;
    @Autowired
    private SiteSnapshotMapper siteSnapshotMapper;

    @Override
    public Boolean addArticle(Integer typeId, String title, String content, String digest) {
        try {
            Article article = genArticle(typeId, title, content, digest);
            // 执行 插入成功后，mybatis会返回当前记录的主键ID，并赋值给当前对象
            articleMapper.insertArticle(article);

            ArticleSnapshot snapshot = genArticleSnapshot(article);
            articleSnapshotMapper.addArticleSnap(snapshot);

            siteSnapshotMapper.updateAccessCount(KeyIdConstant.SITE_SNAPSHOT_ID, 1);
            return true;
        } catch (Exception e) {
            log.error("添加文章异常， e： ", e);
        }

        return false;
    }

    @Override
    public void updateArticle(Long articleId, Integer typeId, String title, String content, String digest) {
        Article article = genArticle(typeId, title, content, digest);
        article.setArticleId(articleId);
        articleMapper.updateArticle(article);

        ArticleSnapshot snapshot = genArticleSnapshot(article);
        articleSnapshotMapper.updateArticleSnap(snapshot);
    }

    @Override
    public Article getArticle(Long articleId) {
        Article article = articleMapper.getById(articleId);
        return article;
    }

    @Override
    public List<ArticleSnapshot> listArticleSnap(Integer page) {
        if (page <= 0) {
            page = 1;
        }
        int offset = (page - 1) * ADMIN_ARTICLE_PAGE_SIZE;
        return articleSnapshotMapper.listArticleSnap(offset, ADMIN_ARTICLE_PAGE_SIZE);
    }

    @Override
    public List<ArticleSnapshot> listShowArticleSnap(Integer typeId, Integer page) {
        if (page <= 0) {
            page = 1;
        }
        int offset = (page - 1) * ARTICLE_PAGE_SIZE;
        if (typeId > 0) {
            return articleSnapshotMapper.listShowArticleSnapByTypeId(typeId, offset, ARTICLE_PAGE_SIZE);
        }
        return articleSnapshotMapper.listShowArticleSnap(offset, ARTICLE_PAGE_SIZE);
    }

    @Override
    public void increaseReadCount(Long articleId) {
        articleSnapshotMapper.updateArticleReadCount(articleId, 1);
    }

    @Override
    public void increaseCommentCount(Long articleId) {
        articleSnapshotMapper.updateArticleCommentCount(articleId, 1);
        siteSnapshotMapper.updateCommentCount(KeyIdConstant.SITE_SNAPSHOT_ID, 1);
    }


    private Article genArticle(Integer typeId, String title, String content, String digest) {
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
        snapshot.setTypeId(article.getTypeId());
        snapshot.setArticleId(article.getArticleId());
        snapshot.setDigest(article.getDigest());
        snapshot.setTitle(article.getTitle());
        snapshot.setPublishTime(article.getGmtCreate());
        snapshot.setGmtCreate(new Date());
        snapshot.setGmtUpdate(new Date());
        return snapshot;
    }
}

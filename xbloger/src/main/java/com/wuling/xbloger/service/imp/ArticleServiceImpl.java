package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.constant.KeyIdConstant;
import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.Contribution;
import com.wuling.xbloger.entity.bo.ArchiveBO;
import com.wuling.xbloger.entity.bo.ArticleInfoBO;
import com.wuling.xbloger.entity.bo.HomeArticleBO;
import com.wuling.xbloger.entity.vo.ArticleTitleVO;
import com.wuling.xbloger.enumeration.ContributionEnum;
import com.wuling.xbloger.mapper.ArticleMapper;
import com.wuling.xbloger.mapper.ArticleSnapshotMapper;
import com.wuling.xbloger.mapper.ContributionMapper;
import com.wuling.xbloger.mapper.SiteSnapshotMapper;
import com.wuling.xbloger.service.ArticleService;
import com.wuling.xbloger.util.DateUtil;
import com.wuling.xbloger.util.ObjectBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.*;

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
    @Autowired
    private ContributionMapper contributionMapper;

    @Override
    public Boolean addArticle(Long typeId, String title, String content, String digest) {
        try {
            Article article = genArticle(typeId, title, content, digest);
            // 执行 插入成功后，mybatis会返回当前记录的主键ID，并赋值给当前对象
            articleMapper.insert(article);

            ArticleSnapshot snapshot = genArticleSnapshot(article);
            articleSnapshotMapper.insert(snapshot);

            siteSnapshotMapper.updateArticleCount(KeyIdConstant.SITE_SNAPSHOT_ID, 1);

            contributionMapper.insert(ContributionEnum.CREATE_ARTICLE.getTypeId(), ContributionEnum.CREATE_ARTICLE.getDesc());
            return true;
        } catch (Exception e) {
            log.error("添加文章异常， e： ", e);
        }

        return false;
    }

    @Override
    public void updateArticle(Long articleId, Long typeId, String title, String content, String digest) {
        Article article = genArticle(typeId, title, content, digest);
        article.setArticleId(articleId);
        articleMapper.updateArticle(article);

        ArticleSnapshot snapshot = genArticleSnapshot(article);
        articleSnapshotMapper.updateArticleSnap(snapshot);

        contributionMapper.insert(ContributionEnum.UPDATE_ARTICLE.getTypeId(), ContributionEnum.UPDATE_ARTICLE.getDesc());
    }

    @Override
    public ArticleInfoBO getArticleInfoBo(Long articleId) {
        ArticleInfoBO articleInfoBo = articleSnapshotMapper.getArticleInfoBoByArticleId(articleId);
        Optional.ofNullable(articleInfoBo).ifPresent(a -> a.setPublishTimestamp(a.getPublishTime().getTime()));
        return articleInfoBo;
    }

    @Override
    public Map<Long, List<ArticleTitleVO>> listArchives() {
        List<ArticleSnapshot> snapshots = articleSnapshotMapper.listAllArticleWithBasicInfo();
        if (snapshots != null && !snapshots.isEmpty()) {
            Map<Long, List<ArticleTitleVO>> map = new HashMap<>();
            for (ArticleSnapshot s : snapshots) {
                LocalDateTime publishTime = DateUtil.date2LocalDateTime(s.getPublishTime());
                LocalDateTime localDateTime = publishTime.withDayOfMonth(1).withHour(0).withMinute(0)
                        .withSecond(0).withNano(0);
                long second = localDateTime.toEpochSecond(ZoneOffset.of("+8"));
                List<ArticleTitleVO> list = null;
                if (map.containsKey(second)) {
                    list = map.get(second);
                } else {
                    list = new ArrayList<>();
                }

                ArticleTitleVO titleVO = ObjectBuilder.buildArticleTitleVo(s);
                list.add(titleVO);

                map.put(second, list);
            }

            return map;
        }
        return Collections.emptyMap();
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
    public List<ArticleSnapshot> listShowArticleSnap(Long typeId, Integer page) {
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
    public HomeArticleBO getHotArticle() {
        ArticleSnapshot hotArticle = articleSnapshotMapper.getHotArticle();
        HomeArticleBO articleBO = ObjectBuilder.buildHomeArticleBO(2, hotArticle);
        return articleBO;
    }

    @Override
    public HomeArticleBO getLatestArticle() {
        ArticleSnapshot latestArticle = articleSnapshotMapper.getLatestArticle();
        HomeArticleBO articleBO = ObjectBuilder.buildHomeArticleBO(1, latestArticle);
        return articleBO;
    }

    @Override
    public List<ArticleSnapshot> listHotArticleLimit7() {
        return articleSnapshotMapper.listHotArticles();
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

    @Override
    public void updateArticleShowState(Long articleId, Integer showState) {
        articleSnapshotMapper.updateArticleShowState(articleId, String.valueOf(showState));
    }


    private Article genArticle(Long typeId, String title, String content, String digest) {
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

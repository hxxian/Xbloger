package com.wuling.xbloger.util;

import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.ArticleType;
import com.wuling.xbloger.entity.SiteSnapshot;
import com.wuling.xbloger.entity.bo.ArticleInfoBO;
import com.wuling.xbloger.entity.bo.HomeArticleBO;
import com.wuling.xbloger.entity.vo.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author: wu_ling
 * @Date: 2020/5/17
 * @Desc: 对象构建器工具类
 */
public class ObjectBuilder {

    public static ArticleTypeVO buildArticleTypeVo(ArticleType articleType) {
        ArticleTypeVO articleTypeVo = new ArticleTypeVO();
        if (articleType != null) {
            articleTypeVo.setTypeId(articleType.getTypeId());
            articleTypeVo.setTypeName(articleType.getTypeName());
        }
        return articleTypeVo;
    }

    public static ArticleTitleVO buildArticleTitleVo(ArticleSnapshot snapshot) {
        ArticleTitleVO articleTitleVo = new ArticleTitleVO();
        if (snapshot != null) {
            articleTitleVo.setArticleId(snapshot.getArticleId());
            articleTitleVo.setArticleTitle(snapshot.getTitle());
            articleTitleVo.setPublishTime(snapshot.getPublishTime().getTime());
        }
        return articleTitleVo;
    }

    public static HomeArticleBO buildHomeArticleBO(Integer tag, ArticleSnapshot snapshot) {
        HomeArticleBO articleBO = new HomeArticleBO();
        if (snapshot != null) {
            articleBO.setTag(tag);
            articleBO.setArticleId(snapshot.getArticleId());
            articleBO.setPublishTimestamp(snapshot.getPublishTime().getTime());
            articleBO.setTitle(snapshot.getTitle());
            articleBO.setReadCount(snapshot.getReadCount());
            articleBO.setDigest(snapshot.getDigest());
        }
        return articleBO;
    }

    public static ArticleInfoVO buildArticleInfoVo(ArticleInfoBO article) {
        ArticleInfoVO articleInfoVo = new ArticleInfoVO();
        if (article != null) {
            articleInfoVo.setPublishTimestamp(article.getPublishTimestamp());
            articleInfoVo.setArticleId(article.getArticleId());
            articleInfoVo.setTitle(article.getTitle());
            articleInfoVo.setTypeId(article.getTypeId());
            articleInfoVo.setContent(article.getContent());
            articleInfoVo.setTypeName(article.getTypeName());
            articleInfoVo.setReadCount(article.getReadCount());
        }
        return articleInfoVo;
    }

    public static List<ArchiveVO> buildArchiveVo(Map<Long, List<ArticleTitleVO>> map) {
        if (map == null || map.isEmpty()) {
            return Collections.emptyList();
        }
        List<ArchiveVO> archiveVOs = new ArrayList<>(map.size());
        for (Map.Entry<Long, List<ArticleTitleVO>> entry : map.entrySet()) {
            ArchiveVO archiveVO = new ArchiveVO();
            archiveVO.setArticleTitles(entry.getValue());

            LocalDateTime localDateTime = LocalDateTime
                    .ofEpochSecond(entry.getKey(), 0, ZoneOffset.of("+8"));
            String dateGroup = localDateTime.getYear() + "-" + localDateTime.getMonthValue();
            archiveVO.setDateGroup(dateGroup);

            archiveVOs.add(archiveVO);
        }
        return archiveVOs;
    }

    public static SiteSnapshotVO buildSiteSnapshotVO(SiteSnapshot site) {
        SiteSnapshotVO vo = new SiteSnapshotVO();
        if (site != null) {
            vo.setAccessCount(site.getAccessCount());
            vo.setArticleCount(site.getArticleCount());
            vo.setCommentCount(site.getCommentCount());
            vo.setFoundingDays(DateUtil.getDayPeriodForNow(site.getFoundingTime()));
        }
        return vo;
    }
}

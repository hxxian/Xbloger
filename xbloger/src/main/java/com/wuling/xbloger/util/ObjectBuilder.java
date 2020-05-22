package com.wuling.xbloger.util;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.ArticleType;
import com.wuling.xbloger.entity.bo.ArticleInfoBo;
import com.wuling.xbloger.entity.bo.HomeArticleBO;
import com.wuling.xbloger.entity.vo.ArticleInfoVo;
import com.wuling.xbloger.entity.vo.ArticleTitleVo;
import com.wuling.xbloger.entity.vo.ArticleTypeVo;

/**
 * @Author: wu_ling
 * @Date: 2020/5/17
 * @Desc: 对象构建器工具类
 */
public class ObjectBuilder {

    public static ArticleTypeVo buildArticleTypeVo(ArticleType articleType) {
        ArticleTypeVo articleTypeVo = new ArticleTypeVo();
        if (articleType != null) {
            articleTypeVo.setTypeId(articleType.getTypeId());
            articleTypeVo.setTypeName(articleType.getTypeName());
        }
        return articleTypeVo;
    }

    public static ArticleTitleVo buildArticleTitleVo(ArticleSnapshot snapshot) {
        ArticleTitleVo articleTitleVo = new ArticleTitleVo();
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

    public static ArticleInfoVo buildArticleInfoVo(ArticleInfoBo article) {
        ArticleInfoVo articleInfoVo = new ArticleInfoVo();
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
}

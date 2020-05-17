package com.wuling.xbloger.util;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.ArticleType;
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
        }
        return articleTitleVo;
    }

    // TODO 待完善
    public static ArticleInfoVo buildArticleInfoVo(Article article) {
        ArticleInfoVo articleInfoVo = new ArticleInfoVo();
        if (article != null) {
            articleInfoVo.setPublishTimestamp(article.getGmtCreate().getTime());
            articleInfoVo.setArticleId(article.getArticleId());
            articleInfoVo.setTitle(article.getTitle());
            articleInfoVo.setContent(article.getContent());
        }
        return articleInfoVo;
    }
}

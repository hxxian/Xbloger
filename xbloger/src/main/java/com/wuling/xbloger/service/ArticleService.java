package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc:
 */
public interface ArticleService {

    /**
     * 新增文章
     *
     * @param typeId 分类ID
     * @param title 标题
     * @param content 内容
     * @param digest 摘要
     * @return
     */
    Boolean addArticle(Integer typeId, String title, String content, String digest);

    /**
     *  更新文章
     *
     * @param articleId
     * @param typeId
     * @param title
     * @param content
     * @param digest
     */
    void updateArticle(Long articleId, Integer typeId, String title, String content, String digest);

    /**
     * 根据文章ID返回文章详情信息
     *
     * @param articleId 文章ID
     * @return
     */
    Article getArticle(Long articleId);

    /**
     * 分页查询文章快照列表
     *
     * @param page
     * @return
     */
    List<ArticleSnapshot> listArticleSnap(Integer page);
}

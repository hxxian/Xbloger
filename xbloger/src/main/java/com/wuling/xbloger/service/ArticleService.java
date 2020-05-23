package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.bo.ArchiveBO;
import com.wuling.xbloger.entity.bo.ArticleInfoBO;
import com.wuling.xbloger.entity.bo.HomeArticleBO;
import com.wuling.xbloger.entity.vo.ArticleTitleVO;

import java.util.List;
import java.util.Map;

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
    ArticleInfoBO getArticleInfoBo(Long articleId);

    /**
     * 查询所有文章基本信息
     * 用于归档数据查询
     *
     * @return
     */
    Map<Long, List<ArticleTitleVO>> listArchives();

    /**
     * 查询文章详情
     *
     * @param articleId
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

    /**
     * 分页查询显示的文章快照列表
     *
     * @param typeId
     * @param page
     * @return
     */
    List<ArticleSnapshot> listShowArticleSnap(Integer typeId, Integer page);

    /**
     * 获取最热文章
     *
     * @return
     */
    HomeArticleBO getHotArticle();

    /**
     * 获取最新文章
     *
     * @return
     */
    HomeArticleBO getLatestArticle();

    /**
     * 查询访问量最高的前7条
     *
     * @return
     */
    List<ArticleSnapshot> listHotArticleLimit7();

    /**
     * 访问数自增1
     *
     * @param articleId
     */
    void increaseReadCount(Long articleId);

    /**
     * 评论数自增1
     *
     * @param articleId
     */
    void increaseCommentCount(Long articleId);

    /**
     * 更新文章的显示状态
     *
     * @param articleId
     * @param showState
     */
    void updateArticleShowState(Long articleId, Integer showState);
}

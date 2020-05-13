package com.wuling.xbloger.service;

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
     * @return
     */
    Boolean addArticle(Integer typeId, String title, String content);

}

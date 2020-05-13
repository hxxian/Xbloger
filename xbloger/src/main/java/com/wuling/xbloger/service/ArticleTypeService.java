package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.ArticleType;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/14
 * @Desc: 文章分类业务逻辑
 */
public interface ArticleTypeService {


    /**
     * 新增文章分类
     *
     * @param typeName
     */
    void addArticleType(String typeName);

    /**
     * 查询全部文章分类
     *
     * @return
     */
    List<ArticleType> listArticleType();

}

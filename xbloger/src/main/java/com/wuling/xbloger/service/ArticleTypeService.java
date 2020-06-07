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

    /**
     * 更新文章类别名
     *
     * @param typeId
     * @param typeName
     */
    void updateTypeName(Long typeId, String typeName);

    /**
     * 删除文章类别
     * 注：如果有文章关联着该分类，则不允许删除
     *
     * @param typeId
     */
    boolean deleteType(Long typeId);
}

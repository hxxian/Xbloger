package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface ArticleMapper {

    @Select("select * from article_info where id = #{articleId}")
    Article getById(Long articleId);

    @Insert("insert into article_info(type_id, title, content, digest, gmt_create, gmt_update) " +
            "values(#{typeId}, #{title}, #{content}, #{digest}, #{gmtCreate}, #{gmtUpdate})")
    @Options(useGeneratedKeys = true, keyProperty = "articleId")
    void insertArticle(Article article);
}

package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.Article;
import org.apache.ibatis.annotations.*;

public interface ArticleMapper {

    @Select("select * from article_info where id = #{articleId}")
    @Results({
            @Result(property = "articleId", column = "id"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "gmtCreate", column = "gmt_create")
    })
    Article getById(Long articleId);

    @Insert("insert into article_info(type_id, title, content, digest, gmt_create, gmt_update) " +
            "values(#{typeId}, #{title}, #{content}, #{digest}, #{gmtCreate}, #{gmtUpdate})")
    @Options(useGeneratedKeys = true, keyProperty = "articleId")
    void insertArticle(Article article);

    @Update("update article_info set type_id = #{typeId}, title = #{title}, digest = #{digest}, content = #{content}, " +
            "gmt_update = #{gmtUpdate} where id = #{articleId}")
    void updateArticle(Article article);
}

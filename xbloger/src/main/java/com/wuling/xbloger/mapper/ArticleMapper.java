package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.Article;
import org.apache.ibatis.annotations.*;

public interface ArticleMapper extends BaseRecordMapper<Article> {

    @Select("select a.*, t.type_name from article_info a, article_type t where a.id = #{articleId} and a.type_id = t.id")
    @Results({
            @Result(property = "articleId", column = "id"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "gmtCreate", column = "gmt_create")
    })
    @Override
    Article getById(Long articleId);

    @Insert("insert into article_info(type_id, title, content, digest, gmt_create, gmt_update) " +
            "values(#{typeId}, #{title}, #{content}, #{digest}, #{gmtCreate}, #{gmtUpdate})")
    @Options(useGeneratedKeys = true, keyProperty = "articleId")
    @Override
    void insert(Article article);

    @Update("update article_info set type_id = #{typeId}, title = #{title}, digest = #{digest}, content = #{content}, " +
            "gmt_update = #{gmtUpdate} where id = #{articleId}")
    void updateArticle(Article article);
}

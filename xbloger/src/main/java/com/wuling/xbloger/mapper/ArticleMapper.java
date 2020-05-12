package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface ArticleMapper {

    @Select("select * from article where id = #{articleId}")
    Article getById(Long articleId);

    @Insert("insert into article(type_id, title, content, gmt_create, gmt_update) " +
            "values(#{typeId}, #{title}, #{content}, #{gmtCreate}, #{gmtUpdate})")
    void insertArticle(Article article);
}

package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.ArticleType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc: 文章分类mapper
 */
public interface ArticleTypeMapper {

    @Insert("insert into article_type(type_name, gmt_create, gmt_update) values(#{typeName}, #{gmtCreate}, #{gmtUpdate})")
    void insertArticleType(ArticleType articleType);

    @Select("select id, type_name, gmt_create from article_type")
    @Results({
            @Result(property = "typeId" ,column = "id"),
            @Result(property = "typeName" ,column = "type_name"),
            @Result(property = "gmtCreate" ,column = "gmt_create"),
    })
    List<ArticleType> listAll();
}

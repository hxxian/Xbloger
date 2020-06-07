package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.ArticleType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc: 文章分类mapper
 */
public interface ArticleTypeMapper extends BaseRecordMapper<ArticleType> {

    @Insert("insert into article_type(type_name, gmt_create, gmt_update) values(#{typeName}, #{gmtCreate}, #{gmtUpdate})")
    @Override
    void insert(ArticleType articleType);

    @Select("select id, type_name, gmt_create from article_type")
    @Results(id = "articleResultMap",value = {
            @Result(property = "typeId" ,column = "id"),
            @Result(property = "typeName" ,column = "type_name"),
            @Result(property = "gmtCreate" ,column = "gmt_create"),
    })
    List<ArticleType> listAll();

    @Select("select id, type_name, gmt_create from article_type where id = #{id}")
    @ResultMap("articleResultMap")
    @Override
    ArticleType getById(Long id);

    @Update("update article_type set type_name = #{newTypeName} where id = #{typeId}")
    void updateTypeName(Long typeId, String newTypeName);

    @Delete("delete from article_type where id = #{id}")
    @Override
    void delete(Long id);
}

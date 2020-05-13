package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.ArticleSnapshot;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleSnapshotMapper {

    @Select("select * from article_snapshot where id >= " +
            "(select id from article_snapshot limit #{offset}, 1 order by id) order by id limit #{pageSize} ")
    List<ArticleSnapshot> listArticleSnap(Integer offset, Integer pageSize);

    @Insert("insert into article_snapshot(aid, title, digest, publish_time, gmt_create, gmt_update) " +
            "values(#{articleId}, #{title}, #{digest}, #{publishTime}, #{gmrCreate}, #{gmtUpdate})")
    void addArticleSnap(ArticleSnapshot snapshot);


}

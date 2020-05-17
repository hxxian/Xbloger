package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.ArticleSnapshot;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleSnapshotMapper {

    @Select("select * from article_snapshot where id >= " +
            "(select id from article_snapshot limit #{offset}, 1 order by id) order by id limit #{pageSize} ")
    @Results({
            @Result(property = "articleId", column = "article_id"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "showState", column = "show_state"),
            @Result(property = "readCount", column = "read_count"),
            @Result(property = "commentCount", column = "comment_count")
    })
    List<ArticleSnapshot> listArticleSnap(Integer offset, Integer pageSize);

    @Insert("insert into article_snapshot(aid, title, digest, publish_time, gmt_create, gmt_update) " +
            "values(#{articleId}, #{title}, #{digest}, #{publishTime}, #{gmtCreate}, #{gmtUpdate})")
    void addArticleSnap(ArticleSnapshot snapshot);


}

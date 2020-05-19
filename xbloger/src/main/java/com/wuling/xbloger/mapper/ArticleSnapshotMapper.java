package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.bo.ArticleInfoBo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleSnapshotMapper {

    @Select("select a.*, s.read_count from article_info a, article_snapshot s where a.id = #{articleId} and a.id = s.aid")
    @Results({
            @Result(property = "article.typeId", column = "type_id"),
            @Result(property = "article.articleId", column = "aid"),
            @Result(property = "article.content", column = "content"),
            @Result(property = "article.title", column = "title"),
            @Result(property = "readCount", column = "read_count"),
            @Result(property = "article.gmtCreate", column = "gmt_create")
    })
    ArticleInfoBo getArticleInfoBoByArticleId(Long articleId);

    @Select("select * from article_snapshot where aid = #{articleId}")
    @Results(id = "snapshotMap", value = {
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "articleId", column = "aid"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "showState", column = "show_state"),
            @Result(property = "readCount", column = "read_count"),
            @Result(property = "commentCount", column = "comment_count")
    })
    ArticleSnapshot getArticleSnapshotByArticleId(Long articleId);

    @Select("select * from article_snapshot where id >= " +
            "(select id from article_snapshot order by id limit #{offset}, 1) order by id limit #{pageSize} ")
    @ResultMap("snapshotMap")
    List<ArticleSnapshot> listArticleSnap(Integer offset, Integer pageSize);

    @Select("select * from article_snapshot where id >= " +
            "(select id from article_snapshot where show_state = 1 order by id limit #{offset}, 1) " +
            "and show_state = 1 order by id limit #{pageSize}")
    @ResultMap("snapshotMap")
    List<ArticleSnapshot> listShowArticleSnap(Integer offset, Integer pageSize);

    @Select("select * from article_snapshot where id >= " +
            "(select id from article_snapshot where type_id = #{typeId} and show_state = 1 order by id limit #{offset}, 1) " +
            "and type_id = #{typeId} and show_state = 1 order by id limit #{pageSize}")
    @ResultMap("snapshotMap")
    List<ArticleSnapshot> listShowArticleSnapByTypeId(Integer typeId, Integer offset, Integer pageSize);

    @Insert("insert into article_snapshot(typeId, aid, title, digest, publish_time, gmt_create, gmt_update) " +
            "values(#{typeId}, #{articleId}, #{title}, #{digest}, #{publishTime}, #{gmtCreate}, #{gmtUpdate})")
    void addArticleSnap(ArticleSnapshot snapshot);

    @Update("update article_snapshot set type_id = #{typeId}, title = #{title}, digest = #{digest}, gmt_update = #{gmtUpdate} where aid = #{articleId}")
    void updateArticleSnap(ArticleSnapshot snapshot);

    @Update("update article_snapshot set read_count = read_count + #{increment} where aid = #{articleId}")
    void updateArticleReadCount(Long articleId, Integer increment);

    @Update("update article_snapshot set comment_count = comment_count + #{increment} where aid = #{articleId}")
    void updateArticleCommentCount(Long articleId, Integer increment);
}

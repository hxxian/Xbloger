package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.bo.ArticleInfoBO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleSnapshotMapper extends BaseMapper<ArticleSnapshot> {

    @Select("select a.id, a.type_id, a.title, a.content, t.type_name, s.read_count, s.publish_time from article_info a, " +
            "article_type t, article_snapshot s where a.id = #{articleId} and a.id = s.aid and a.type_id = t.id")
    @Results({
            @Result(property = "articleId", column = "id"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "readCount", column = "read_count"),
            @Result(property = "publishTime", column = "publish_time")
    })
    ArticleInfoBO getArticleInfoBoByArticleId(Long articleId);

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
    List<ArticleSnapshot> listShowArticleSnapByTypeId(Long typeId, Integer offset, Integer pageSize);

    /**
     * 查询最新文章
     *
     * @return
     */
    @Select("select * from article_snapshot order by id desc limit 1")
    @ResultMap("snapshotMap")
    ArticleSnapshot getLatestArticle();

    /**
     * 查询浏览数最多的文章
     *
     * @return
     */
    @Select("select * from article_snapshot  where read_count = (select MAX(read_count) from article_snapshot)")
    @ResultMap("snapshotMap")
    ArticleSnapshot getHotArticle();

    @ResultMap("snapshotMap")
    @Select("select id, aid, title, publish_time from article_snapshot order by read_count desc limit 7")
    List<ArticleSnapshot> listHotArticles();

    /**
     * 查询全部文章基本信息（id、title、publish_time、read_count）
     * 用于归档数据处理
     *
     * @return
     */
    @ResultMap("snapshotMap")
    @Select("select aid, title, publish_time, read_count from article_snapshot")
    List<ArticleSnapshot> listAllArticleWithBasicInfo();

    @Insert("insert into article_snapshot(type_id, aid, title, digest, publish_time, gmt_create, gmt_update) " +
            "values(#{typeId}, #{articleId}, #{title}, #{digest}, #{publishTime}, #{gmtCreate}, #{gmtUpdate})")
    @Override
    void insert(ArticleSnapshot snapshot);

    @Update("update article_snapshot set type_id = #{typeId}, title = #{title}, digest = #{digest}, gmt_update = #{gmtUpdate} where aid = #{articleId}")
    void updateArticleSnap(ArticleSnapshot snapshot);

    @Update("update article_snapshot set read_count = read_count + #{increment} where aid = #{articleId}")
    void updateArticleReadCount(Long articleId, Integer increment);

    @Update("update article_snapshot set comment_count = comment_count + #{increment} where aid = #{articleId}")
    void updateArticleCommentCount(Long articleId, Integer increment);

    @Update("update article_snapshot set show_state = #{showState} where aid = #{articleId}")
    void updateArticleShowState(Long articleId, String showState);
}

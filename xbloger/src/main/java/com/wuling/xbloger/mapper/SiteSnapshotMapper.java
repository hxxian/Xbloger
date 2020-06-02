package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.SiteSnapshot;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: wu_ling
 * @Date: 2020/5/19
 * @Desc: 网站快照信息
 */
public interface SiteSnapshotMapper {

    @Update("update site_snapshot set access_count = access_count + #{increment} where id = #{siteId}")
    void updateAccessCount(Integer siteId, Integer increment);

    @Update("update site_snapshot set comment_count = comment_count + #{increment} where id = #{siteId}")
    void updateCommentCount(Integer siteId, Integer increment);

    @Update("update site_snapshot set article_count = article_count + #{increment} where id = #{siteId}")
    void updateArticleCount(Integer siteId, Integer increment);

    @Select("select * from site_snapshot where id = #{siteId}")
    @Results({
            @Result(property = "siteId", column = "id"),
            @Result(property = "accessCount", column = "access_count"),
            @Result(property = "commentCount", column = "comment_count"),
            @Result(property = "articleCount", column = "article_count"),
            @Result(property = "foundingTime", column = "founding_time")
    })
    SiteSnapshot getSiteSnapshot(Integer siteId);

}

package com.wuling.xbloger.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wuling.xbloger.entity.SiteSnapshot;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: wu_ling
 * @Date: 2020/5/19
 * @Desc: 网站快照信息
 */
public interface SiteSnapshotMapper extends BaseMapper<SiteSnapshot> {

    @Update("update site_snapshot set access_count = access_count + #{increment} where id = #{siteId}")
    void updateAccessCount(Integer siteId, Integer increment);

    @Update("update site_snapshot set comment_count = comment_count + #{increment} where id = #{siteId}")
    void updateCommentCount(Integer siteId, Integer increment);

    @Update("update site_snapshot set article_count = article_count + #{increment} where id = #{siteId}")
    void updateArticleCount(Integer siteId, Integer increment);
}

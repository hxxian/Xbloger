package com.wuling.xbloger.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xian
 * @Description: 网站信息快照
 * @Date:create in 2020/5/12 8:02
 */
@Setter
@Getter
@TableName("site_snapshot")
public class SiteSnapshot {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer siteId;

    // 总访问次数
    @TableField("access_count")
    private Integer accessCount;
    // 总评论数
    @TableField("comment_count")
    private Integer commentCount;
    // 总文章数
    @TableField("article_count")
    private Integer articleCount;
    // 成立时间
    @TableField("founding_time")
    private Date foundingTime;

}

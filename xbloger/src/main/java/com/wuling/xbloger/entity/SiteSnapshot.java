package com.wuling.xbloger.entity;

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
public class SiteSnapshot {

    private Integer siteId;

    // 总访问次数
    private Integer accessCount;
    // 总评论数
    private Integer commentCount;
    // 总文章数
    private Integer articleCount;
    // 成立时间
    private Date foundingTime;

}

package com.wuling.xbloger.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc:
 */
@Getter
@Setter
public class SiteSnapshotVO {

    private Integer articleCount;
    private Integer commentCount;
    private Integer accessCount;
    // 成立天数
    private Integer foundingDays;

}

package com.wuling.xbloger.entity.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/6/2
 * @Desc: 最新评论BO
 */
@Getter
@Setter
public class LatestCommentBO {

    private Long commentId;
    private String content;
    private Long commentTime;

}

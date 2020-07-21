package com.wuling.xbloger.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xian
 * @Description: 评论
 * @Date:create in 2020/5/12 8:17
 */
@Setter
@Getter
public class Comment extends Entity {

    // 评论ID
    private Long commentId;
    // 文章ID，若不是针对文章的评论（针对博主的评论）则为0
    private Long articleId;
    // 回复的评论ID
    private Long replyCommentId;
    // 头像Url
    private String avatarUrl;
    // 昵称
    private String nickname;
    // 邮箱
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String email;
    // 站点
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String website;
    // ip地址
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String ipAddr;
    // 评论内容
    private String content;
    // 显示状态
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer showState;
    // 评论时间
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date gmtCreate;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date gmtUpdate;


    // 扩展字段
    @JsonIgnore
    private long groupId;
}

package com.wuling.xbloger.entity.vo;

import com.wuling.xbloger.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/7/17
 * @Desc:
 */
@Getter
@Setter
public class CommentVO {

    private Long sessionId;
    private Long timestamp;
    private List<Comment> comments;

}

package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.Comment;

/**
 * @Author: wu_ling
 * @Date: 2020/5/28
 * @Desc: 评论
 */
public interface CommentService {

    /**
     * 保存评论记录
     *
     * @param comment
     */
    void saveComment(Comment comment);

}

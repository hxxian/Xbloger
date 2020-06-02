package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.Comment;
import com.wuling.xbloger.entity.bo.LatestCommentBO;

import java.util.List;

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

    /**
     * 查询最新10条评论
     *
     * @return
     */
    List<LatestCommentBO> listLatest10Comment();

}

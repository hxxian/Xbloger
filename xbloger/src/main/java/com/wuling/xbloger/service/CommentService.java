package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.Comment;
import com.wuling.xbloger.entity.bo.LatestCommentBO;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询某一篇文章下的所有评论
     *
     * @param articleId
     * @return
     */
    List<Comment> listCommentByArticleId(Long articleId);

    /**
     * 分页查询评论数据
     *
     * @param page
     * @return
     */
    List<Comment> listCommentWithPage(Integer page);

    /**
     * 更新评论的显示状态
     *
     * @param state
     * @param commentId
     */
    void updateCommentShowState(Long commentId, Integer state);
}

package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.Comment;
import com.wuling.xbloger.entity.bo.LatestCommentBO;
import com.wuling.xbloger.mapper.CommentMapper;
import com.wuling.xbloger.service.CommentService;
import com.wuling.xbloger.util.ObjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: wu_ling
 * @Date: 2020/5/28
 * @Desc: TODO
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void saveComment(Comment comment) {
        if (comment != null) {
            comment.setGmtCreate(new Date());
            comment.setGmtUpdate(new Date());
        }
        commentMapper.insert(comment);
    }

    @Override
    public List<LatestCommentBO> listLatest10Comment() {
        List<Comment> comments = commentMapper.listLatest10Comment();
        if (comments != null && !comments.isEmpty()) {
            List<LatestCommentBO> bos = new ArrayList<>(comments.size());
            for (Comment c : comments) {
                bos.add(ObjectBuilder.buildLatestCommentBO(c));
            }
            return bos;
        }
        return Collections.emptyList();
    }

    @Override
    public List<Comment> listCommentByArticleId(Long articleId) {
        List<Comment> comments = commentMapper.listCommentByArticleId(articleId);

        return comments;
    }
}

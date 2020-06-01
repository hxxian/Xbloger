package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.Comment;
import com.wuling.xbloger.mapper.CommentMapper;
import com.wuling.xbloger.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
            comment.setGmtCreate(new Date());
        }
        commentMapper.insertComment(comment);
    }
}

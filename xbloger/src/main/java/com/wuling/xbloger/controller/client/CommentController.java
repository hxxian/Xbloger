package com.wuling.xbloger.controller.client;

import com.wuling.xbloger.entity.Comment;
import com.wuling.xbloger.service.CommentService;
import com.wuling.xbloger.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wu_ling
 * @Date: 2020/5/28
 * @Desc: 网站评论控制器
 */
@RestController
@RequestMapping("comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> saveComment(HttpServletRequest request, Comment comment) {
        String ip = IpUtil.getIpAddr(request);
        if (comment != null) {
            comment.setIpAddr(ip);
        }
        try {
            commentService.saveComment(comment);
        } catch (Exception e) {
            log.error("新增评论异常. error: [{}]", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}

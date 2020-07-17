package com.wuling.xbloger.controller.client;

import com.wuling.xbloger.controller.R;
import com.wuling.xbloger.entity.Comment;
import com.wuling.xbloger.enumeration.ResultCodeEnum;
import com.wuling.xbloger.service.CommentService;
import com.wuling.xbloger.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public ResponseEntity<R> saveComment(HttpServletRequest request, Comment comment) {
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
        return ResponseEntity.status(HttpStatus.CREATED).body(new R(ResultCodeEnum.OP_SUCCESS, null));
    }

    @GetMapping("list/aid/{aid}")
    public ResponseEntity<R> listComment(@PathVariable("aid") Long articleId) {
        if (articleId == null) {
            return ResponseEntity.ok(new R(ResultCodeEnum.QUERY_SUCCESS, null));
        }
        List<Comment> comments = commentService.listCommentByArticleId(articleId);
        return ResponseEntity.ok(new R(ResultCodeEnum.QUERY_SUCCESS, comments));
    }

}

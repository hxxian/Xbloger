package com.wuling.xbloger.controller.admin;

import com.wuling.xbloger.annotation.OperateRecord;
import com.wuling.xbloger.controller.R;
import com.wuling.xbloger.entity.Comment;
import com.wuling.xbloger.enumeration.ResultCodeEnum;
import com.wuling.xbloger.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/11/15
 * @Desc: 后台管理，评论controller
 */
@RestController
@RequestMapping("admin/comment")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("list/{page}")
    public ResponseEntity<R> listComment(@PathVariable("page") Integer page) {
        List<Comment> comments = commentService.listCommentWithPage(page);

        R result = new R(ResultCodeEnum.QUERY_SUCCESS, comments);
        return ResponseEntity.ok(result);
    }

    @PostMapping("update/state")
    @OperateRecord("更新评论显示状态")
    public ResponseEntity<R> updateShowState(Long commentId, Integer showState) {
        commentService.updateCommentShowState(commentId, showState);
        R result = new R(ResultCodeEnum.OP_SUCCESS, true);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
    }

}

package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.constant.CommentAvatarConstant;
import com.wuling.xbloger.constant.KeyIdConstant;
import com.wuling.xbloger.entity.Comment;
import com.wuling.xbloger.entity.bo.LatestCommentBO;
import com.wuling.xbloger.mapper.CommentMapper;
import com.wuling.xbloger.service.CommentService;
import com.wuling.xbloger.util.ObjectBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Author: wu_ling
 * @Date: 2020/5/28
 * @Desc: TODO
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private static final int PAGE_SIZE = 10;

    private String latestUsedAvatarUrl;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void saveComment(Comment comment) {
        if (comment != null) {
            comment.setGmtCreate(new Date());
            comment.setGmtUpdate(new Date());
        }
        // 做好博主回复与游客回复的区分
        if (!KeyIdConstant.ADMIN_NAME.equals(comment.getNickname())) {
            Comment commentUser = commentMapper.getByName(comment.getNickname());
            Optional.ofNullable(commentUser).ifPresent(c -> comment.setAvatarUrl(c.getAvatarUrl()));

            if (commentUser == null) {

                Random random = new Random(System.currentTimeMillis());
                int size = CommentAvatarConstant.COMMENT_AVATARS.size();
                int idx = random.nextInt(size);
                String avatarUrl = CommentAvatarConstant.COMMENT_AVATARS.get(idx);

                if (!StringUtils.isEmpty(latestUsedAvatarUrl)) {
                    int randomTimes = 10;
                    while (avatarUrl.equals(latestUsedAvatarUrl) && randomTimes > 0) {
                        idx = random.nextInt(size);
                        avatarUrl = CommentAvatarConstant.COMMENT_AVATARS.get(idx);
                        randomTimes--;
                    }
                }
                latestUsedAvatarUrl = avatarUrl;

                comment.setAvatarUrl(avatarUrl);
            }
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

    @Override
    public List<Comment> listCommentWithPage(Integer page) {
        if (page <= 0) {
            page = 1;
        }
        int offset = (page - 1) * PAGE_SIZE;

        List<Comment> comments = commentMapper.listCommentByPage(offset, PAGE_SIZE);
        return comments;
    }

    @Override
    public void updateCommentShowState(Long commentId, Integer state) {
        int res = commentMapper.updateShowState(commentId, String.valueOf(state));
        log.info("update res: {}", res);
    }
}

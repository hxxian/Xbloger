package com.wuling.xbloger.util;

import com.wuling.xbloger.entity.*;
import com.wuling.xbloger.entity.bo.ArticleInfoBO;
import com.wuling.xbloger.entity.bo.HomeArticleBO;
import com.wuling.xbloger.entity.bo.LatestCommentBO;
import com.wuling.xbloger.entity.vo.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @Author: wu_ling
 * @Date: 2020/5/17
 * @Desc: 对象构建器工具类
 */
public class ObjectBuilder {

    public static ArticleTypeVO buildArticleTypeVo(ArticleType articleType) {
        ArticleTypeVO articleTypeVo = new ArticleTypeVO();
        if (articleType != null) {
            articleTypeVo.setTypeId(articleType.getTypeId());
            articleTypeVo.setTypeName(articleType.getTypeName());
            if (articleType.getGmtCreate() != null) {
                articleTypeVo.setGmtCreate(articleType.getGmtCreate().getTime());
            }
            articleTypeVo.setArticleCount(articleType.getArticleCount() == null
                    ? 0 : articleType.getArticleCount());
        }
        return articleTypeVo;
    }

    public static ArticleTitleVO buildArticleTitleVo(ArticleSnapshot snapshot) {
        ArticleTitleVO articleTitleVo = new ArticleTitleVO();
        if (snapshot != null) {
            articleTitleVo.setArticleId(snapshot.getArticleId());
            articleTitleVo.setArticleTitle(snapshot.getTitle());
            articleTitleVo.setPublishTime(snapshot.getPublishTime().getTime());
            articleTitleVo.setReadCount(snapshot.getReadCount());
        }
        return articleTitleVo;
    }

    public static HomeArticleBO buildHomeArticleBO(Integer tag, ArticleSnapshot snapshot) {
        HomeArticleBO articleBO = new HomeArticleBO();
        if (snapshot != null) {
            articleBO.setTag(tag);
            articleBO.setArticleId(snapshot.getArticleId());
            articleBO.setPublishTimestamp(snapshot.getPublishTime().getTime());
            articleBO.setTitle(snapshot.getTitle());
            articleBO.setReadCount(snapshot.getReadCount());
            articleBO.setDigest(snapshot.getDigest());
        }
        return articleBO;
    }

    public static ArticleInfoVO buildArticleInfoVo(ArticleInfoBO article) {
        ArticleInfoVO articleInfoVo = new ArticleInfoVO();
        if (article != null) {
            articleInfoVo.setPublishTimestamp(article.getPublishTimestamp());
            articleInfoVo.setArticleId(article.getArticleId());
            articleInfoVo.setTitle(article.getTitle());
            articleInfoVo.setTypeId(article.getTypeId());
            articleInfoVo.setContent(article.getContent());
            articleInfoVo.setTypeName(article.getTypeName());
            articleInfoVo.setReadCount(article.getReadCount());
        }
        return articleInfoVo;
    }

    public static List<ArchiveVO> buildArchiveVo(Map<Long, List<ArticleTitleVO>> map) {
        if (map == null || map.isEmpty()) {
            return Collections.emptyList();
        }
        List<ArchiveVO> archiveVOs = new ArrayList<>(map.size());
        for (Map.Entry<Long, List<ArticleTitleVO>> entry : map.entrySet()) {
            ArchiveVO archiveVO = new ArchiveVO();
            archiveVO.setArticleTitles(entry.getValue());

            LocalDateTime localDateTime = LocalDateTime
                    .ofEpochSecond(entry.getKey(), 0, ZoneOffset.of("+8"));
            String dateGroup = localDateTime.getYear() + "-" + localDateTime.getMonthValue();
            archiveVO.setDateGroup(dateGroup);
            archiveVO.setMonthTimestamp(entry.getKey());

            archiveVOs.add(archiveVO);
        }
        Collections.sort(archiveVOs, (a1, a2) -> (int) (a2.getMonthTimestamp() - a1.getMonthTimestamp()));

        return archiveVOs;
    }

    public static SiteSnapshotVO buildSiteSnapshotVO(SiteSnapshot site) {
        SiteSnapshotVO vo = new SiteSnapshotVO();
        if (site != null) {
            vo.setAccessCount(site.getAccessCount());
            vo.setArticleCount(site.getArticleCount());
            vo.setCommentCount(site.getCommentCount());
            vo.setFoundingDays(DateUtil.getDayDurationForNow(site.getFoundingTime()));
        }
        return vo;
    }

    public static LatestCommentBO buildLatestCommentBO(Comment comment) {
        LatestCommentBO bo = new LatestCommentBO();
        if (comment != null) {
            bo.setCommentId(comment.getCommentId());
            bo.setContent(comment.getContent());
            bo.setCommentTime(comment.getGmtCreate().getTime());
        }
        return bo;
    }

    public static OpRecordVO buildOpRecordVo(OpRecord record) {
        OpRecordVO vo = new OpRecordVO();
        if (record != null) {
            vo.setId(record.getId());
            vo.setOpDesc(record.getOpDesc());
            vo.setOpUser(record.getOpUser());
            vo.setIpAddr(record.getIpAddr());
            vo.setGmtCreate(record.getGmtCreate().getTime());
        }
        return vo;
    }

    public static List<CommentVO> buildCommentVO(List<Comment> comments) {
        if (comments == null || comments.isEmpty()) {
            return Collections.emptyList();
        }

        List<CommentVO> commentVOS = new ArrayList<>();
        Map<Long, List<Comment>> map = new HashMap<>();
        Map<Long, Comment> parentMap = new HashMap<>();

        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            c.setIpAddr(null);
            c.setEmail(null);
            c.setWebsite(null);
            c.setShowState(null);
            c.setGmtUpdate(null);

            if (c.getReplyCommentId() <= 0) {
                CommentVO vo = new CommentVO();
                vo.setSessionId(c.getCommentId());
                vo.setTimestamp(c.getGmtCreate().getTime());
                List<Comment> list = new ArrayList<>(1);
                c.setGroupId(c.getCommentId());
                list.add(c);

                vo.setComments(list);
                map.put(c.getCommentId(), list);
                parentMap.put(c.getCommentId(), c);
                continue;
            }

            List<Comment> list = null;
            long groupId = 0;

            if (parentMap.containsKey(c.getReplyCommentId())) {
                Comment parentComment = parentMap.get(c.getReplyCommentId());
                groupId = parentComment.getGroupId();
            }

            if (map.containsKey(groupId)) {
                list = map.get(groupId);
            }

            if (list == null) {
                list = new ArrayList<>();
            }

            c.setGroupId(groupId);

            list.add(c);
            map.put(groupId, list);
            parentMap.put(c.getCommentId(), c);

        }

        for (Map.Entry<Long, List<Comment>> entry : map.entrySet()) {
            CommentVO vo = new CommentVO();
            vo.setSessionId(entry.getKey());
            vo.setTimestamp(entry.getValue().get(0).getGmtCreate().getTime());
            vo.setComments(entry.getValue());

            commentVOS.add(vo);
        }

        Collections.sort(commentVOS, (c1, c2) -> (int) (c1.getTimestamp() - c2.getTimestamp()));

        map.clear();
        parentMap.clear();

        return commentVOS;
    }

}

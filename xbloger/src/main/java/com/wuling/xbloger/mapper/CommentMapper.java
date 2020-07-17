package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/28
 * @Desc: 评论
 */
public interface CommentMapper extends BaseMapper<Comment> {

    @Insert("insert into comment(aid, reply_id, avatar_url, nickname, email, website, ip_addr, content, gmt_create, gmt_update) " +
            "values(#{articleId}, #{replyCommentId}, #{avatarUrl}, #{nickname}, #{email}, #{website}, #{ipAddr}, #{content}, " +
            "#{gmtCreate}, #{gmtUpdate})")
    @Override
    void insert(Comment comment);

    @Select("select * from comment where id >= (select id from comment order by id asc limit #{offset}, 1) order by id asc limit #{limit}")
    @Results(id = "commentMap", value = {
            @Result(property = "commentId", column = "id"),
            @Result(property = "replyCommentId", column = "reply_id"),
            @Result(property = "articleId", column = "aid"),
            @Result(property = "avatarUrl", column = "avatar_url"),
            @Result(property = "ipAddr", column = "ip_addr"),
            @Result(property = "showState", column = "show_state"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtUpdate", column = "gmt_update")
    })
    List<Comment> listCommentByPage(Integer offset, Integer limit);

    @Select("select * from comment order by id desc limit 10")
    @ResultMap("commentMap")
    List<Comment> listLatest10Comment();

    @Select("select * from comment where aid = #{articleId} order by id")
    @ResultMap("commentMap")
    List<Comment> listCommentByArticleId(Long articleId);
}

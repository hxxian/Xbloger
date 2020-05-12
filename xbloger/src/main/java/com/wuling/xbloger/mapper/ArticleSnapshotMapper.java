package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.ArticleSnapshot;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleSnapshotMapper {

    @Select("select * from article_snapshot where id >= " +
            "(select id from article_snapshot limit #{offset}, 1 order by id) limit #{offset}, #{pageSize} order by id")
    List<ArticleSnapshot> listArticleSnap(Integer offset, Integer pageSize);

}

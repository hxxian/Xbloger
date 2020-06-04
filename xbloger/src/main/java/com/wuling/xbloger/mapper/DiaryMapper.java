package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.Diary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/15
 * @Desc: 日记、说说
 */
public interface DiaryMapper {

    @Insert("insert into diary(content, gmt_create, gmt_update) values(#{content}, #{gmtCreate}, #{gmtUpdate})")
    void insertDiary(Diary diary);

    @Select("select content, gmt_create from diary order by id desc limit #{limit}")
    @Results({@Result(property = "gmtCreate", column = "gmt_create")})
    List<Diary> listDiary(Integer limit);

}

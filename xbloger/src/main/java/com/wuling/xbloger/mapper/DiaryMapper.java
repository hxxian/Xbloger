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
public interface DiaryMapper extends BaseRecordMapper<Diary> {

    @Insert("insert into diary(content, gmt_create, gmt_update) values(#{content}, #{gmtCreate}, #{gmtUpdate})")
    @Override
    void insert(Diary diary);

    @Select("select content, gmt_create from diary where id = #{id}")
    @Results({@Result(property = "gmtCreate", column = "gmt_create")})
    @Override
    Diary getById(Long id);

    @Select("select content, gmt_create from diary order by id desc limit #{limit}")
    @Results({@Result(property = "gmtCreate", column = "gmt_create")})
    List<Diary> listDiary(Integer limit);

}

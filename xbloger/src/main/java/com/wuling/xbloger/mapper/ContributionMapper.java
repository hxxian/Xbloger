package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.Contribution;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/6/20
 * @Desc:
 */
public interface ContributionMapper {

    @Insert("insert into contribution (type, des, gmt_create, gmt_update) values (#{type}, #{des}, now(), now())")
    void insert(Integer type, String des);

    @Select("select * from contribution order by id desc limit #{limit}")
    @Results(id = "contributionMap", value = {
            @Result(property = "gmtCreate", column = "gmt_create")
    })
    List<Contribution> listContributionByYear(Integer limit);

}

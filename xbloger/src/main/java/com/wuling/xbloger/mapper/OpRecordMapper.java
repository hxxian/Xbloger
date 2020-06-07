package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.Entity;
import com.wuling.xbloger.entity.OpRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/6/7
 * @Desc:
 */
public interface OpRecordMapper extends BaseMapper<OpRecord> {

    @Select("select * from op_record where id = #{id}")
    @Results(id = "opRecordResultMap", value = {
            @Result(property = "opDesc", column = "op_desc"),
            @Result(property = "opUser", column = "op_user"),
            @Result(property = "ipAddr", column = "ip_addr"),
            @Result(property = "gmtCreate", column = "gmt_create")
    })
    OpRecord getById(Long id);

    @Override
    @Insert("insert into op_record(op_desc, op_user, ip_addr, gmt_create, gmt_update) values (#{opDesc}, #{opUser}, #{ipAddr}, now(), now())")
    void insert(OpRecord entity);

    @Select("select * from op_record where id <= (select id from op_record order by id desc limit #{offset}, 1) order by id desc limit #{limit}")
    @ResultMap("opRecordResultMap")
    List<OpRecord> listByPage(Integer offset, Integer limit);
}

package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.Entity;
import com.wuling.xbloger.entity.OpRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: wu_ling
 * @Date: 2020/6/7
 * @Desc:
 */
public interface OpRecordMapper extends BaseMapper<OpRecord> {

    @Select("select * from op_record where id = #{id}")
    OpRecord getById(Long id);

    @Override
    @Insert("insert into op_record(op_desc, op_user, ip_addr, gmt_create, gmt_update) values (#{opDesc}, #{opUser}, #{ipAddr}, now(), now())")
    void insert(OpRecord entity);
}

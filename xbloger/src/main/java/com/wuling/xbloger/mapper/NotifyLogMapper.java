package com.wuling.xbloger.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NotifyLogMapper {

    @Select("select * from notify_log")
    @Results({
            @Result(property = "logId", column = "log_id"),
            @Result(property = "total", column = "total"),
            @Result(property = "aSuccess", column = "a_success"),
            @Result(property = "iSuccess", column = "i_success"),
            @Result(property = "aFail", column = "a_fail"),
            @Result(property = "iFail", column = "i_fail"),
            @Result(property = "aNotNeed", column = "a_not_need"),
            @Result(property = "iNotNeed", column = "i_not_need"),
            @Result(property = "extend", column = "extend"),
    })
    List<NotifyLog> listNotifyLog();

}

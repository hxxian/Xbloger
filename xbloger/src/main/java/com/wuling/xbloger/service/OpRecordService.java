package com.wuling.xbloger.service;

import com.wuling.xbloger.entity.OpRecord;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/6/7
 * @Desc:
 */
public interface OpRecordService {

    /**
     * 分页查询操作记录
     *
     * @param page
     * @return
     */
    List<OpRecord> listByPage(Integer page);

}

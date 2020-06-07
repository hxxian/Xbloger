package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.OpRecord;
import com.wuling.xbloger.mapper.OpRecordMapper;
import com.wuling.xbloger.service.OpRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/6/7
 * @Desc:
 */
@Service
public class OpRecordServiceImpl implements OpRecordService {

    private static final int PAGE_SIZE = 8;

    @Autowired
    private OpRecordMapper opRecordMapper;

    @Override
    public List<OpRecord> listByPage(Integer page) {
        if (page <= 0) {
            page = 1;
        }
        int offset = (page - 1) * PAGE_SIZE;

        return opRecordMapper.listByPage(offset, PAGE_SIZE);
    }
}

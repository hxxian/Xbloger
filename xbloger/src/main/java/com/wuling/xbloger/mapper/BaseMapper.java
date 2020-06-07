package com.wuling.xbloger.mapper;

import com.wuling.xbloger.entity.Entity;

/**
 * @Author: wu_ling
 * @Date: 2020/6/7
 * @Desc:
 */
public interface BaseMapper<T> {

    void insert(T entity);

}

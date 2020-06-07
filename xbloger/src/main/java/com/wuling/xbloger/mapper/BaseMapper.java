package com.wuling.xbloger.mapper;

/**
 * @Author: wu_ling
 * @Date: 2020/6/7
 * @Desc:
 */
public interface BaseMapper<T> {

    T getById(Long id);

    void insert(T entity);

    void delete(Long id);
}

package com.wuling.xbloger.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/22
 * @Desc: 归档页数据对象
 */
@Getter
@Setter
public class ArchiveVO {

    private String dateGroup;
    private List<ArticleTitleVO> articleTitles;

}

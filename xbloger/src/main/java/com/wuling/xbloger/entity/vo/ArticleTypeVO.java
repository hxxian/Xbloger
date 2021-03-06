package com.wuling.xbloger.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/5/17
 * @Desc: 文章分类VO
 */
@Getter
@Setter
public class ArticleTypeVO {

    private Long typeId;
    private String typeName;
    private Long gmtCreate;
    private Integer articleCount;

}

package com.wuling.xbloger.controller.admin.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/5/17
 * @Desc:
 */
@Getter
@Setter
public class AddArticleReq {

    private Long typeId;
    private Long articleId;
    private String title;
    private String digest;
    private String content;

}

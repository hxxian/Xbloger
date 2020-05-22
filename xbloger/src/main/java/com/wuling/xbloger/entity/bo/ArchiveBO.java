package com.wuling.xbloger.entity.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/22
 * @Desc:
 */
@Getter
@Setter
public class ArchiveBO {

    private Long dateGroupTimestamp;
    private List<ArticleInfoBO> articleInfos;

}

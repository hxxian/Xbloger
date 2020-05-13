package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.mapper.ArticleMapper;
import com.wuling.xbloger.mapper.ArticleSnapshotMapper;
import com.wuling.xbloger.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wu_ling
 * @Date: 2020/5/13
 * @Desc:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleSnapshotMapper articleSnapshotMapper;

    @Override
    public Boolean addArticle(Integer typeId, String title, String content) {
        // TODO
        return false;
    }

}

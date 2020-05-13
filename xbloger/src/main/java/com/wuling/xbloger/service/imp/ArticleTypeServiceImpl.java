package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.ArticleType;
import com.wuling.xbloger.mapper.ArticleTypeMapper;
import com.wuling.xbloger.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/14
 * @Desc:
 */
@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @Override
    public void addArticleType(String typeName) {
        ArticleType articleType = buildArticleType(typeName);
        articleTypeMapper.addArticleType(articleType);
    }

    @Override
    public List<ArticleType> listArticleType() {
        List<ArticleType> articleTypes = articleTypeMapper.listAll();
        return articleTypes;
    }

    private ArticleType buildArticleType(String typeName) {
        ArticleType articleType = new ArticleType();
        articleType.setTypeName(typeName);
        articleType.setGmtCreate(new Date());
        articleType.setGmtUpdate(new Date());
        return articleType;
    }
}

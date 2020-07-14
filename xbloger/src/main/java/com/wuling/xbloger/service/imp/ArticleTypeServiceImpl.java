package com.wuling.xbloger.service.imp;

import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.ArticleType;
import com.wuling.xbloger.mapper.ArticleSnapshotMapper;
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
    @Autowired
    private ArticleSnapshotMapper articleSnapshotMapper;

    @Override
    public void addArticleType(String typeName) {
        ArticleType articleType = buildArticleType(typeName);
        articleTypeMapper.insert(articleType);
    }

    @Override
    public List<ArticleType> listArticleType() {
        List<ArticleType> articleTypes = articleTypeMapper.listAll();
        return articleTypes;
    }

    @Override
    public List<ArticleType> listTypeWithCount() {
        return articleTypeMapper.listTypeWithCount();
    }

    @Override
    public void updateTypeName(Long typeId, String typeName) {
        articleTypeMapper.updateTypeName(typeId, typeName);
    }

    @Override
    public boolean deleteType(Long typeId) {
        List<ArticleSnapshot> snapshots = articleSnapshotMapper.listShowArticleSnapByTypeId(typeId, 0, 1);
        if (snapshots != null && !snapshots.isEmpty()) {
            return false;
        }
        articleTypeMapper.delete(typeId);
        return true;
    }

    private ArticleType buildArticleType(String typeName) {
        ArticleType articleType = new ArticleType();
        articleType.setTypeName(typeName);
        articleType.setGmtCreate(new Date());
        articleType.setGmtUpdate(new Date());
        return articleType;
    }
}

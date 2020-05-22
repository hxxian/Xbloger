package com.wuling.xbloger.controller.client;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.ArticleType;
import com.wuling.xbloger.entity.bo.ArticleInfoBo;
import com.wuling.xbloger.entity.vo.ArticleInfoVo;
import com.wuling.xbloger.entity.vo.ArticleTitleVo;
import com.wuling.xbloger.entity.vo.ArticleTypeVo;
import com.wuling.xbloger.service.ArticleService;
import com.wuling.xbloger.service.ArticleTypeService;
import com.wuling.xbloger.util.ObjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/16
 * @Desc: TODO
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private ArticleService articleService;

    /**
     * 查询文章类型列表
     *
     * @return
     */
    @GetMapping("type/list")
    public ResponseEntity<List<ArticleTypeVo>> listArticleType() {
        List<ArticleType> articleTypes = articleTypeService.listArticleType();
        if (articleTypes != null && !articleTypes.isEmpty()) {
            List<ArticleTypeVo> typeVos = new ArrayList<>(articleTypes.size());
            for (ArticleType type : articleTypes) {
                typeVos.add(ObjectBuilder.buildArticleTypeVo(type));
            }
            return ResponseEntity.ok(typeVos);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    /**
     * 分页查询指定类别的文章快照列表
     *
     * @param typeId
     * @param page
     * @return
     */
    @GetMapping("page/{page}")
    public ResponseEntity<List<ArticleTitleVo>> listArticleByPage(Integer typeId, @PathVariable Integer page) {
        List<ArticleSnapshot> snapshots = articleService.listShowArticleSnap(typeId, page);
        if (snapshots != null && !snapshots.isEmpty()) {
            List<ArticleTitleVo> titleVos = new ArrayList<>(snapshots.size());
            for (ArticleSnapshot snapshot : snapshots) {
                ArticleTitleVo titleVo = ObjectBuilder.buildArticleTitleVo(snapshot);
                titleVos.add(titleVo);
            }

            return ResponseEntity.ok(titleVos);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    /**
     * 查询文章详情
     *
     * @param articleId
     * @return
     */
    @GetMapping("info/{articleId}")
    public ResponseEntity<ArticleInfoVo> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleInfoBo infoBo = articleService.getArticleInfoBo(articleId);
        if (infoBo != null) {
            ArticleInfoVo infoVo = ObjectBuilder.buildArticleInfoVo(infoBo);
            articleService.increaseReadCount(articleId);
            return ResponseEntity.ok(infoVo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


}

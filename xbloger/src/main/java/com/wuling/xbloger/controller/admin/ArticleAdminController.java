package com.wuling.xbloger.controller.admin;

import com.wuling.xbloger.annotation.OperateRecord;
import com.wuling.xbloger.controller.admin.request.AddArticleReq;
import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.ArticleType;
import com.wuling.xbloger.entity.vo.ArticleTypeVO;
import com.wuling.xbloger.service.ArticleService;
import com.wuling.xbloger.service.ArticleTypeService;
import com.wuling.xbloger.util.ObjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/15
 * @Desc: 管理员文章控制器
 */
@RestController
@RequestMapping("admin/article")
public class ArticleAdminController {

    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private ArticleService articleService;


    @PostMapping("type")
    @OperateRecord("新增文章类别")
    public ResponseEntity<Void> addArticleType(String typeName) {
        articleTypeService.addArticleType(typeName);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("type/update")
    @OperateRecord("修改文章类别名")
    public ResponseEntity<Void> updateArticleType(Long typeId, String typeName) {
        articleTypeService.updateTypeName(typeId, typeName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("type/delete")
    @OperateRecord("删除文章类别")
    public ResponseEntity<Void> deleteArticleType(Long typeId) {
        boolean res = articleTypeService.deleteType(typeId);
        if (res) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @GetMapping("type")
    public ResponseEntity<List<ArticleTypeVO>> listArticleType() {
        List<ArticleType> articleTypes = articleTypeService.listArticleType();
        if (articleTypes != null && !articleTypes.isEmpty()) {
            List<ArticleTypeVO> typeVos = new ArrayList<>(articleTypes.size());
            for (ArticleType type : articleTypes) {
                typeVos.add(ObjectBuilder.buildArticleTypeVo(type));
            }
            return ResponseEntity.ok(typeVos);
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping()
    public ResponseEntity<Void> addOrUpdateArticle(AddArticleReq req) {
        if (req.getArticleId() != null && req.getArticleId() > 0) {
            // 更新
            articleService.updateArticle(req.getArticleId(), req.getTypeId(), req.getTitle(), req.getContent(), req.getDigest());

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        articleService.addArticle(req.getTypeId(), req.getTitle(), req.getContent(), req.getDigest());

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("show")
    @OperateRecord("更新文章发布状态")
    public ResponseEntity<Void> updateArticleShowState(Long articleId, Integer showState) {
        articleService.updateArticleShowState(articleId, showState);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("page/{page}")
    public ResponseEntity<List<ArticleSnapshot>> listArticleSnaps(@PathVariable("page") Integer page) {
        List<ArticleSnapshot> snapshots = articleService.listArticleSnap(page);
        return ResponseEntity.ok(snapshots);
    }

    @GetMapping("info/{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable("articleId") Long articleId) {
        Article article = articleService.getArticle(articleId);
        return ResponseEntity.ok(article);
    }
}

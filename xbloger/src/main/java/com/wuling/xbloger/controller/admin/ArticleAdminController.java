package com.wuling.xbloger.controller.admin;

import com.wuling.xbloger.controller.admin.request.AddArticleReq;
import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleType;
import com.wuling.xbloger.service.ArticleService;
import com.wuling.xbloger.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Void> addArticleType(String typeName) {
        articleTypeService.addArticleType(typeName);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("type")
    public ResponseEntity<List<ArticleType>> listArticleType() {
        List<ArticleType> articleTypes = articleTypeService.listArticleType();
        return ResponseEntity.ok(articleTypes);
    }

    @PostMapping()
    public ResponseEntity<Void> addOrUpdateArticle(AddArticleReq req) {
        if (req.getArticleId() != null) {
            // TODO 更新
        } else {
            articleService.addArticle(req.getTypeId(), req.getTitle(), req.getContent(), req.getDigest());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}

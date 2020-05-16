package com.wuling.xbloger.controller.client;

import com.wuling.xbloger.entity.Article;
import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.ArticleType;
import com.wuling.xbloger.entity.vo.AricleVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/5/16
 * @Desc: TODO
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @GetMapping("type/list")
    public ResponseEntity<List<ArticleType>> listArticleType() {
        return null;
    }


    @GetMapping("page/${page}")
    public ResponseEntity<List<ArticleSnapshot>> listArticleByPage(@PathVariable(value = "page") Integer page) {
        return null;
    }

    @GetMapping("info/${articleId}")
    public ResponseEntity<AricleVo> getArticle(@PathVariable(value = "articleId") Long articleId) {
        return null;
    }


}

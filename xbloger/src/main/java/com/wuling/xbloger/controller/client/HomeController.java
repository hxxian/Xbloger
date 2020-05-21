package com.wuling.xbloger.controller.client;

import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.Diary;
import com.wuling.xbloger.entity.bo.HomeArticleBO;
import com.wuling.xbloger.entity.vo.ArticleTitleVo;
import com.wuling.xbloger.entity.vo.HomeVo;
import com.wuling.xbloger.service.ArticleService;
import com.wuling.xbloger.service.DiaryService;
import com.wuling.xbloger.util.ObjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: wu_ling
 * @Date: 2020/5/16
 * @Desc: 首页访问控制器
 */
@RestController
@RequestMapping("home")
public class HomeController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private DiaryService diaryService;

    @GetMapping
    public ResponseEntity<HomeVo> getHomeVo() {
        HomeVo homeVo = new HomeVo();
        List<HomeArticleBO> articleBOList = new ArrayList<>(2);
        HomeArticleBO hotArticle = articleService.getHotArticle();
        HomeArticleBO latestArticle = articleService.getLatestArticle();
        Optional.ofNullable(latestArticle).ifPresent(a -> articleBOList.add(a));
        Optional.ofNullable(hotArticle).ifPresent(a -> articleBOList.add(a));
        homeVo.setArticles(articleBOList);

        List<ArticleSnapshot> snapshots = articleService.listHotArticleLimit7();
        if (snapshots != null && !snapshots.isEmpty()) {
            List<ArticleTitleVo> articleTitleVos
                    = snapshots.stream().map(a -> ObjectBuilder.buildArticleTitleVo(a)).collect(Collectors.toList());
            Optional.ofNullable(articleTitleVos).ifPresent(a -> homeVo.setArticleTitles(a));
        }

        Diary latestDiary = diaryService.getLatestDiary();
        Optional.ofNullable(latestDiary).ifPresent(d -> homeVo.setDiaryContent(d.getContent()));

        return ResponseEntity.ok(homeVo);
    }

}

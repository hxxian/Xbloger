package com.wuling.xbloger.controller.client;

import com.wuling.xbloger.controller.R;
import com.wuling.xbloger.entity.ArticleSnapshot;
import com.wuling.xbloger.entity.ArticleType;
import com.wuling.xbloger.entity.Diary;
import com.wuling.xbloger.entity.bo.HomeArticleBO;
import com.wuling.xbloger.entity.vo.ArticleTitleVO;
import com.wuling.xbloger.entity.vo.ArticleTypeVO;
import com.wuling.xbloger.entity.vo.HomeVO;
import com.wuling.xbloger.enumeration.ResultCodeEnum;
import com.wuling.xbloger.service.ArticleService;
import com.wuling.xbloger.service.ArticleTypeService;
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
    @Autowired
    private ArticleTypeService articleTypeService;

    @GetMapping
    public ResponseEntity<HomeVO> getHomeVo() {
        HomeVO homeVo = new HomeVO();
        List<HomeArticleBO> articleBOList = new ArrayList<>(2);
        HomeArticleBO hotArticle = articleService.getHotArticle();
        HomeArticleBO latestArticle = articleService.getLatestArticle();
        Optional.ofNullable(latestArticle).ifPresent(a -> articleBOList.add(a));
        Optional.ofNullable(hotArticle).ifPresent(a -> articleBOList.add(a));
        homeVo.setArticles(articleBOList);

        List<ArticleSnapshot> snapshots = articleService.listHotArticleLimit7();
        if (snapshots != null && !snapshots.isEmpty()) {
            List<ArticleTitleVO> articleTitleVos
                    = snapshots.stream().map(a -> ObjectBuilder.buildArticleTitleVo(a)).collect(Collectors.toList());
            Optional.ofNullable(articleTitleVos).ifPresent(a -> homeVo.setArticleTitles(a));
        }

        List<ArticleType> types = articleTypeService.listTypeWithCount();
        if (types != null && !types.isEmpty()) {
            List<ArticleTypeVO> typeVOS = new ArrayList<>(types.size());
            for (ArticleType type : types) {
                ArticleTypeVO typeVO = ObjectBuilder.buildArticleTypeVo(type);
                typeVOS.add(typeVO);
            }
            homeVo.setArticleTypes(typeVOS);
        }

        Diary latestDiary = diaryService.getLatestDiary();
        Optional.ofNullable(latestDiary).ifPresent(d -> {
            homeVo.setDiaryContent(d.getContent());
            homeVo.setDiaryTimestamp(d.getGmtCreate().getTime());
        });

        return ResponseEntity.ok(homeVo);
    }

    @GetMapping("diary")
    public ResponseEntity<R> getLatestDiary() {
        Diary latestDiary = diaryService.getLatestDiary();
        if (latestDiary != null) {
            latestDiary.setTimestamp(latestDiary.getGmtCreate().getTime());
        }
        R result = new R(ResultCodeEnum.QUERY_SUCCESS, latestDiary);
        return ResponseEntity.ok(result);
    }

}

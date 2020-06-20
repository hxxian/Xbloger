package com.wuling.xbloger.controller.client;

import com.wuling.xbloger.controller.R;
import com.wuling.xbloger.entity.bo.ContributionBO;
import com.wuling.xbloger.enumeration.ResultCodeEnum;
import com.wuling.xbloger.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wu_ling
 * @Date: 2020/6/20
 * @Desc:
 */
@RequestMapping("contribution")
@RestController
public class ContributionController {

    @Autowired
    private ContributionService contributionService;

    @GetMapping("list")
    public ResponseEntity<R> listContribution() {
        ContributionBO bo = contributionService.listContributionInCurrYear();
        return ResponseEntity.status(HttpStatus.OK).body(new R(ResultCodeEnum.QUERY_SUCCESS, bo));
    }

}

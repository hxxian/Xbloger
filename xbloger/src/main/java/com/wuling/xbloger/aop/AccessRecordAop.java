package com.wuling.xbloger.aop;

import com.wuling.xbloger.constant.KeyIdConstant;
import com.wuling.xbloger.entity.SiteSnapshot;
import com.wuling.xbloger.mapper.SiteSnapshotMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: wu_ling
 * @Date: 2020/5/16
 * @Desc: 访问登记
 */
@Aspect
@Component
@Slf4j
public class AccessRecordAop {

    @Autowired
    private SiteSnapshotMapper siteSnapshotMapper;

    @Pointcut("execution(* com.wuling.xbloger.controller.client.*.*(..))")
    public void cutController() {}

    @Before("cutController()")
    public void accessRecord(JoinPoint point) {
        log.info("AccessRecordAop: start record something......");
        // 访问数增加1
        siteSnapshotMapper.updateAccessCount(KeyIdConstant.SITE_SNAPSHOT_ID, 1);
    }

}

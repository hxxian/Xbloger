package com.wuling.xbloger.aop;

import com.wuling.xbloger.annotation.OperateRecord;
import com.wuling.xbloger.constant.KeyIdConstant;
import com.wuling.xbloger.entity.OpRecord;
import com.wuling.xbloger.mapper.OpRecordMapper;
import com.wuling.xbloger.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author: wu_ling
 * @Date: 2020/6/7
 * @Desc:
 */
@Aspect
@Component
@Slf4j
public class AdminOpRecordAop {

    @Autowired
    private OpRecordMapper opRecordMapper;

    @Pointcut("execution(* com.wuling.xbloger.controller.admin.*.*(..)) && !execution(* com.wuling.xbloger.controller.admin.FileController.*(..))")
    public void pointCut() {}

    @After("pointCut()")
    public void record(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        Class<?>[] argTypes = new Class[joinPoint.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        Method method = null;
        try {
            method = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (method == null) {
            return;
        }
        OperateRecord operateRecord = method.getAnnotation(OperateRecord.class);
        if (operateRecord != null) {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = sra.getRequest();

            String opDesc = operateRecord.value();
            String opUser = KeyIdConstant.ADMIN_NAME;
            String ip = IpUtil.getIpAddr(request);
//            log.info("opDesc: [{}], ip: [{}]", opDesc, ip);

            OpRecord opRecord = new OpRecord();
            opRecord.setIpAddr(ip);
            opRecord.setOpDesc(opDesc);
            opRecord.setOpUser(opUser);

            opRecordMapper.insert(opRecord);
        }
    }

}

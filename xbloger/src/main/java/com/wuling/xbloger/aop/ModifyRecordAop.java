package com.wuling.xbloger.aop;

import com.wuling.xbloger.annotation.ModifyRecord;
import com.wuling.xbloger.annotation.RecordColumn;
import com.wuling.xbloger.entity.Record;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @Author: wu_ling
 * @Date: 2020/6/4
 * @Desc: 数据更新记录aop处理
 */
//@Aspect
//@Component
@Slf4j
public class ModifyRecordAop {

    /**
     * 仅仅拦截update方法，参数固定，即BaseDao里的update(Entity entity, Long entityId)
     */
//    @Pointcut("execution(* com.wuling.xbloger.manager..*.update(..))")
    public void cutDataModify() {
    }

//    @Around("cutDataModify()")
    public void recordCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("recordCheck staring...");
        Object[] args = proceedingJoinPoint.getArgs();
        if (args.length > 0) {
//            BaseDao baseDao = (BaseDao) proceedingJoinPoint.getThis();
//            Entity currEntity = (Entity) args[0];
            Long entityId = (Long) args[1];

//            ModifyRecord modifyRecordAnnotation = currEntity.getClass().getAnnotation(ModifyRecord.class);
            ModifyRecord modifyRecordAnnotation = null;
            if (modifyRecordAnnotation != null) {
                // 数据变更的表名
                String tableName = modifyRecordAnnotation.value();

//                Entity oldEntity = baseDao.getById(entityId);

//                Field[] fields = currEntity.getClass().getDeclaredFields();
                Field[] fields = null;
                for (Field field : fields) {
                    field.setAccessible(true);

//                    Object oldVal = field.get(oldEntity);
//                    Object newVal = field.get(currEntity);
                    Object oldVal = null;
                    Object newVal = null;

                    if (oldVal != null && !oldVal.equals(newVal)) {
                        RecordColumn recordColumn = field.getAnnotation(RecordColumn.class);
                        if (recordColumn != null) {
                            Record record = new Record();

                            record.setOpUser("舞零");
                            record.setRecordId(entityId);
                            record.setOldValue(oldVal.toString());
                            record.setNewValue(newVal.toString());

                            record.setRecordTableName(tableName);
                            record.setGmtCreate(new Date());
                            record.setGmtUpdate(new Date());

                            // 数据变更的列名
                            String columnName = recordColumn.value();
                            record.setRecordTableColumn(columnName);

                            log.info("记录数据变更：[{}]", record);
                        }
                    }
                }
            }
        }
        proceedingJoinPoint.proceed(args);

        log.info("recordCheck end!!!\n");
    }

}

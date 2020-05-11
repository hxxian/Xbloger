package com.wuling.xbloger;

import com.wuling.xbloger.entity.NotifyLog;
import com.wuling.xbloger.mapper.NotifyLogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class XblogerApplicationTests {

    @Autowired
    private NotifyLogMapper notifyLogMapper;

    @Test
    void contextLoads() {
        List<NotifyLog> notifyLogs = notifyLogMapper.listNotifyLog();
        if (notifyLogs != null) {
            System.out.println(notifyLogs.size());

        }
    }

}

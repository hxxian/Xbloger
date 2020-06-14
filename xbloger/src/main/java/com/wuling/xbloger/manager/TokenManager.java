package com.wuling.xbloger.manager;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc: token管理器，控制token的生命周期
 */
public class TokenManager {

    private static final int SESSION_CAPACITY = 1;
    private Map<String, Long> sessionTokens;

    // token有效时长12小时
    private static final long TOKEN_VALID_TIME_PERIOD = 60 * 60 * 1000 * 12;

    private TokenManager() {
        sessionTokens = new HashMap<>(SESSION_CAPACITY);
    }

    private static class Singleton {
        private static final TokenManager INSTANCE = new TokenManager();
    }

    public static TokenManager getInstance() {
        return Singleton.INSTANCE;
    }

    public String getToken() {
        String token = UUID.randomUUID().toString();
        // 同时只能存在一个session
        if (!sessionTokens.isEmpty()) {
            sessionTokens.clear();
        }
        sessionTokens.put(token, System.currentTimeMillis());
        return token;
    }

    public void clearTokens() {
        sessionTokens.clear();
    }

    /**
     * 检验token的合法性
     *
     * @param token
     * @return
     */
    public boolean checkTokenIsValid(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        if (!sessionTokens.containsKey(token)) {
            return false;
        }
        long timestamp = sessionTokens.get(token);
        long now = System.currentTimeMillis();
        if (now - timestamp > TOKEN_VALID_TIME_PERIOD) {
            return false;
        }
        return true;
    }

}

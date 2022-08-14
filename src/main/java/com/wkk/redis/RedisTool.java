package com.wkk.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author weikunkun
 * @since 2021/4/15
 */
@Slf4j
public class RedisTool {
    // 用于加锁
    private static final String LOCK_SUCCESS = "OK";
    // 用于设置 string操作的的参数，只在键不存在时，才对键进行设置操作
    private static final String SET_IF_NOT_EXIST = "NX";
    // 用于设置 键的过期时间
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    // 默认过期时间
    private static final int DEFAULT_EXPIRE_TIME = 60;
    //
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 加锁操作
     * @param jedis
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        expireTime = expireTime <= 0 ? DEFAULT_EXPIRE_TIME : expireTime;
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            log.info("get lock success, request_id {}", requestId);
            return true;
        }
        return false;
    }

    /**
     * 解锁操作
     * @param jedis
     * @param lockKey
     * @param requestId
     * @return
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                "then return redis.call('del', KEYS[1]) else return 0 end";

        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            log.info("release lock success, request_id {}", requestId);
            return true;
        }
        return false;

    }
}
